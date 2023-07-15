package org.routemaster.api.auth.domain.user.email.impl.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.routemaster.api.auth.domain.user.email.impl.exception.EmailUserErrorDescription;
import org.routemaster.api.auth.domain.user.email.impl.persistence.EmailUserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailUserDetailsService implements UserDetailsService {

    private final EmailUserRepository basicUserRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return basicUserRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(EmailUserErrorDescription.EMAIL_USER_NOT_FOUND));
    }
}