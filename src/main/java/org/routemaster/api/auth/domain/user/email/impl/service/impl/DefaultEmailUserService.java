package org.routemaster.api.auth.domain.user.email.impl.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.routemaster.api.auth.domain.user.email.impl.data.EmailUser;
import org.routemaster.api.auth.domain.user.email.impl.data.EmailUserReady;
import org.routemaster.api.auth.domain.user.email.impl.exception.EmailUserErrorDescription;
import org.routemaster.api.auth.domain.user.email.impl.persistence.EmailUserReadyRepository;
import org.routemaster.api.auth.domain.user.email.impl.service.EmailUserService;
import org.routemaster.api.auth.domain.user.email.impl.persistence.EmailUserRepository;
import org.routemaster.api.auth.domain.user.email.impl.util.mapper.EmailUserMapper;
import org.routemaster.api.auth.domain.user.impl.exception.UserErrorCode;
import org.routemaster.sdk.exception.data.roe.ROEFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class DefaultEmailUserService implements EmailUserService {

    private final EmailUserReadyRepository emailUserReadyRepository;
    private final EmailUserRepository emailUserRepository;
    private final EmailUserMapper emailUserMapper;
    private final ROEFactory roeFactory;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    @Transactional
    public EmailUser verifyRegister(String username, String verificationCode) {
        if (emailUserRepository.existsByUsername(username)) {
            throw roeFactory.get(
                UserErrorCode.ROE_100,
                EmailUserErrorDescription.EMAIL_USER_ALREADY_EXIST,
                HttpStatus.BAD_REQUEST
            );
        }

        EmailUserReady emailUserReady = emailUserReadyRepository.findByUsernameAndVerificationCode(username, verificationCode)
            .orElseThrow(() -> roeFactory.get(
                UserErrorCode.ROE_102,
                EmailUserErrorDescription.INVALID_USERNAME_OR_VERIFICATION,
                HttpStatus.UNAUTHORIZED
            ));

        EmailUser emailUser = emailUserMapper.register(emailUserReady);
        return emailUserRepository.save(emailUser);
    }


    @Override
    @Transactional
    public EmailUser updatePassword(String id, String password) {
        EmailUser emailUser = details(id);
        emailUser.setPassword(passwordEncoder.encode(password));
        emailUser.setRefreshToken(null);
        return emailUserRepository.save(emailUser);
    }

    @Override
    @Transactional
    public EmailUser updateRefreshToken(String id, String refreshToken) {
        EmailUser emailUser = details(id);
        emailUser.setRefreshToken(refreshToken);
        return emailUserRepository.save(emailUser);
    }
    @Override
    @Transactional
    public void delete(String username) {
        if (!emailUserRepository.existsByUsername(username)) {
            throw roeFactory.get(
                UserErrorCode.ROE_101,
                EmailUserErrorDescription.EMAIL_USER_NOT_FOUND,
                HttpStatus.NOT_FOUND
            );
        }
        emailUserRepository.deleteByUsername(username);
    }

    @Override
    public EmailUser detailsByUsername(String username) {
        log.info("{}", username);
        return emailUserRepository.findByUsername(username)
            .orElseThrow(() -> roeFactory.get(
                UserErrorCode.ROE_101,
                EmailUserErrorDescription.EMAIL_USER_NOT_FOUND,
                HttpStatus.NOT_FOUND
            ));
    }

    @Override
    public EmailUser details(String id) {
        return emailUserRepository.findById(id)
            .orElseThrow(() -> roeFactory.get(
                UserErrorCode.ROE_101,
                EmailUserErrorDescription.EMAIL_USER_NOT_FOUND,
                HttpStatus.NOT_FOUND
            ));
    }
}
