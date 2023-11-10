package org.routemaster.api.auth.domain.user.email.impl.util.mapper;

import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.routemaster.api.auth.domain.user.email.impl.data.EmailUser;
import org.routemaster.api.auth.domain.user.email.impl.data.EmailUserReady;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class EmailUserMapper {

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public EmailUser register(EmailUserReady emailUserReady) {
        return EmailUser.builder()
            .username(emailUserReady.getUsername())
            .password(emailUserReady.getPassword())
            .authorities(emailUserReady.getAuthorities())
            .createdAt(LocalDateTime.now())
            .updatedAt(null)
            .passwordUpdatedAt(emailUserReady.getCreatedAt())
            .lastActiveTime(LocalDateTime.now())
            .lockExpiredAt(null)
            .refreshToken(null)
            .build();
    }

    public EmailUser register(String username, String password) {
        return EmailUser.builder()
            .username(username)
            .password(passwordEncoder.encode(password))
            .authorities(null)
            .createdAt(LocalDateTime.now())
            .updatedAt(null)
            .passwordUpdatedAt(LocalDateTime.now())
            .lastActiveTime(LocalDateTime.now())
            .lockExpiredAt(null)
            .refreshToken(null)
            .build();
    }

    public EmailUser register(String id, String username, String password) {
        return EmailUser.builder()
            .id(id)
            .username(username)
            .password(passwordEncoder.encode(password))
            .authorities(null)
            .createdAt(LocalDateTime.now())
            .updatedAt(null)
            .passwordUpdatedAt(LocalDateTime.now())
            .lastActiveTime(LocalDateTime.now())
            .lockExpiredAt(null)
            .refreshToken(null)
            .build();
    }
}
