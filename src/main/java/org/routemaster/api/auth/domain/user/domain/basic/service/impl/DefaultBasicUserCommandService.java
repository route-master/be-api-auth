package org.routemaster.api.auth.domain.user.domain.basic.service.impl;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.routemaster.api.auth.domain.user.domain.basic.data.BasicUser;
import org.routemaster.api.auth.domain.user.domain.basic.data.BasicUserReady;
import org.routemaster.api.auth.domain.user.domain.basic.data.vo.create.BasicUserCreateVO;
import org.routemaster.api.auth.domain.user.domain.basic.data.vo.details.BasicUserDetailsSecuredResponseVO;
import org.routemaster.api.auth.domain.user.domain.basic.data.vo.response.BasicUserReadySecuredResponseVO;
import org.routemaster.api.auth.domain.user.domain.basic.persistence.BasicUserReadyRepository;
import org.routemaster.api.auth.domain.user.domain.basic.persistence.BasicUserRepository;
import org.routemaster.api.auth.domain.user.domain.basic.service.BasicUserCommandService;
import org.routemaster.api.auth.domain.user.domain.basic.util.email.BasicUserValidationMailUtils;
import org.routemaster.api.auth.domain.user.domain.basic.util.mapper.BasicUserReadyMapper;
import org.routemaster.api.auth.domain.user.util.constant.UserRoleConstant;
import org.routemaster.sdk.exception.data.roe.ROEFactory;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class DefaultBasicUserCommandService implements BasicUserCommandService {

    private final BasicUserRepository basicUserRepository;
    private final BasicUserReadyRepository basicUserReadyRepository;
    private final BasicUserReadyMapper basicUserReadyMapper;
    private final BasicUserValidationMailUtils basicUserValidationMailUtils;
    private final JavaMailSender javaMailSender;
    private final ROEFactory roeFactory;

    @Override
    @Transactional
    public Mono<BasicUserReadySecuredResponseVO> registerBasicUser(BasicUserCreateVO createVO) {
        Set<String> authorities = createVO.getAuthorities();
        if (hasSingleRoleUser(createVO)) {
            return registerBasicUserRoleUser(createVO);
        }
        throw roeFactory.get(null, null, HttpStatus.BAD_REQUEST);
    }

    private Boolean hasSingleRoleUser(BasicUserCreateVO createVO) {
        Set<String> authorities = createVO.getAuthorities();
        return authorities.size() == 1 && authorities.contains(UserRoleConstant.ROLE_USER);
    }

    private Mono<BasicUserReadySecuredResponseVO> registerBasicUserRoleUser(BasicUserCreateVO createVO) {
        String username = createVO.getUsername();
        Mono<Boolean> existsByUsernameMono = basicUserReadyRepository.existsByUsername(username);
        return existsByUsernameMono.flatMap(existsByUsername -> saveRegisterBasicUser(createVO, existsByUsername));
    }

    private Mono<BasicUserReadySecuredResponseVO> saveRegisterBasicUser(BasicUserCreateVO createVO, Boolean existsByUsername) {
        Mono<BasicUserReady> saved = existsByUsername ? updateRegisterBasicUser(createVO) : insertRegisterBasicUser(createVO);
        return saved
                .doOnSuccess(this::sendValidationCode)
                .map(basicUserReadyMapper::toBasicUserReadySecuredResponseVO);
    }

    private Mono<BasicUserReady> insertRegisterBasicUser(BasicUserCreateVO createVO) {
        BasicUserReady forCreate = basicUserReadyMapper.fromCreateVO(createVO);
        return basicUserReadyRepository.save(forCreate);
    }

    private Mono<BasicUserReady> updateRegisterBasicUser(BasicUserCreateVO createVO) {
        String username = createVO.getUsername();
        Mono<BasicUserReady> forUpdateMono = basicUserReadyRepository.findByUsername(username)
                .map(forUpdate -> basicUserReadyMapper.fromCreateVO(forUpdate, createVO));
        return forUpdateMono.flatMap(basicUserReadyRepository::save);
    }

    private void sendValidationCode(BasicUserReady basicUserReady) {
        String email = basicUserReady.getUsername();
        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");

            messageHelper.setTo(email);
            messageHelper.setSubject(basicUserValidationMailUtils.createSubject(basicUserReady));
            messageHelper.setText(basicUserValidationMailUtils.createText(basicUserReady), true);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

        javaMailSender.send(message);
    }

    @Override
    @Transactional
    public BasicUserDetailsSecuredResponseVO validateRegisterBasicUser(Long id, String validationCode) {
        return null;
    }

    @Override
    @Transactional
    public BasicUser updatePassword(Long id, String password) {
        return null;
    }

    @Override
    @Transactional
    public BasicUser updatePassword(String username, String password) {
        return null;
    }

    @Override
    @Transactional
    public BasicUser deleteBasicUser(Long id) {
        return null;
    }

    @Override
    @Transactional
    public BasicUser deleteBasicUser(String username) {
        return null;
    }
}
