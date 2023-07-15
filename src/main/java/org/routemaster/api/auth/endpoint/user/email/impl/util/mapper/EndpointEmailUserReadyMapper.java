package org.routemaster.api.auth.endpoint.user.email.impl.util.mapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.routemaster.api.auth.domain.user.email.impl.data.EmailUserReady;
import org.routemaster.api.auth.endpoint.user.email.impl.vo.request.EmailUserRegisterRequest;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class EndpointEmailUserReadyMapper {

    public EmailUserReady register(EmailUserRegisterRequest request) {
        return EmailUserReady.builder()
            .username(request.getUsername())
            .password(request.getPassword())
            .authorities(request.getAuthorities())
            .build();
    }
}
