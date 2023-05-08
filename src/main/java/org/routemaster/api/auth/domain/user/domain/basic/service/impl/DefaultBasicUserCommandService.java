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
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class DefaultBasicUserCommandService implements BasicUserCommandService {

    private final BasicUserRepository basicUserRepository;
    private final BasicUserReadyRepository basicUserReadyRepository;
    private final BasicUserReadyMapper basicUserReadyMapper;
    private final BasicUserValidationMailUtils basicUserValidationMailUtils;
    private final JavaMailSender javaMailSender;

    @Override
    @Transactional
    public BasicUserReadySecuredResponseVO registerBasicUser(BasicUserCreateVO createVO) {
        BasicUserReady forCreate = basicUserReadyMapper.fromCreateVO(createVO);
        BasicUserReady saved = basicUserReadyRepository.save(forCreate);
        sendValidationCode(saved);
        Map<String, Object> meta = createRegisterBasicUserMetadata(saved);
        return basicUserReadyMapper.toBasicUserReadySecuredResponseVO(saved, meta);
    }

    @Async
     void sendValidationCode(BasicUserReady basicUserReady) {
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

    private Map<String, Object> createRegisterBasicUserMetadata(BasicUserReady basicUserReady) {
        Map<String, Object> meta = new HashMap<>();
        return meta;
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
