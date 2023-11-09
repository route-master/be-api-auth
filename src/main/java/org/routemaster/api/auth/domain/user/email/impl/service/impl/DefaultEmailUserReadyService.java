package org.routemaster.api.auth.domain.user.email.impl.service.impl;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.routemaster.api.auth.domain.user.email.impl.data.EmailUserReady;
import org.routemaster.api.auth.domain.user.email.impl.exception.EmailUserErrorDescription;
import org.routemaster.api.auth.domain.user.email.impl.persistence.EmailUserReadyRepository;
import org.routemaster.api.auth.domain.user.email.impl.persistence.EmailUserRepository;
import org.routemaster.api.auth.domain.user.email.impl.service.EmailUserReadyService;
import org.routemaster.api.auth.domain.user.email.impl.util.email.EmailUserVerificationUtils;
import org.routemaster.api.auth.domain.user.email.impl.util.mapper.EmailUserReadyMapper;
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
public class DefaultEmailUserReadyService implements EmailUserReadyService {

    private final EmailUserReadyRepository emailUserReadyRepository;
    private final EmailUserReadyMapper emailUserReadyMapper;

    private final EmailUserRepository emailUserRepository;

    private final EmailUserVerificationUtils emailUserVerificationUtils;

    private final ROEFactory roeFactory;

    @Override
    @Transactional
    public EmailUserReady register(EmailUserReady user) {
        String username = user.getUsername();

        Optional<EmailUserReady> prev = emailUserReadyRepository.findByUsername(username);
        EmailUserReady basicUserReady = prev.isEmpty() ? emailUserReadyMapper.register(user)
            : emailUserReadyMapper.register(prev.get(), user);

        EmailUserReady saved = emailUserReadyRepository.save(basicUserReady);
        emailUserVerificationUtils.doBeforeVerification(saved);

        return saved;
    }

    @Override
    public EmailUserReady details(String username) {
        return emailUserReadyRepository.findByUsername(username)
            .orElseThrow(() -> roeFactory.get(
                UserErrorCode.ROE_101,
                EmailUserErrorDescription.EMAIL_USER_READY_NOT_FOUND,
                HttpStatus.NOT_FOUND));
    }

    @Override
    @Transactional
    public void delete(String username) {
        if (!emailUserReadyRepository.existsByUsername(username)) {
            throw roeFactory.get(
                UserErrorCode.ROE_101,
                EmailUserErrorDescription.EMAIL_USER_READY_NOT_FOUND,
                HttpStatus.NOT_FOUND
            );
        }
        emailUserReadyRepository.deleteByUsername(username);
    }
}
