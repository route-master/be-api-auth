package org.routemaster.api.auth.domain.user.email.impl.util.mapper;

import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.routemaster.api.auth.domain.user.email.impl.data.EmailUserReady;
import org.routemaster.api.auth.domain.user.impl.util.constant.UserTime;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class EmailUserReadyMapper {

    private final EmailUserVerificationCodeMapper emailUserVerificationCodeMapper;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public EmailUserReady register(EmailUserReady prev, EmailUserReady requested) {
        return EmailUserReady.builder()
            .id(prev.getId())
            .username(prev.getUsername())
            .password(passwordEncoder.encode(requested.getPassword()))
            .authorities(requested.getAuthorities())
            .verificationCode(emailUserVerificationCodeMapper.generate(requested))
            .createdAt(LocalDateTime.now())
            .updatedAt(LocalDateTime.now())
            .expiredAt(LocalDateTime.now().plusDays(UserTime.ACCOUNT_READY_EXPIRATION_DAYS))
            .build();
    }

    public EmailUserReady register(EmailUserReady requested) {
        return EmailUserReady.builder()
            .id(requested.getId())
            .username(requested.getUsername())
            .password(passwordEncoder.encode(requested.getPassword()))
            .authorities(requested.getAuthorities())
            .verificationCode(emailUserVerificationCodeMapper.generate(requested))
            .createdAt(LocalDateTime.now())
            .updatedAt(null)
            .expiredAt(LocalDateTime.now().plusDays(UserTime.ACCOUNT_READY_EXPIRATION_DAYS))
            .build();
    }
}
