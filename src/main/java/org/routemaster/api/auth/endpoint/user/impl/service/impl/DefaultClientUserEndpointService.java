package org.routemaster.api.auth.endpoint.user.impl.service.impl;

import java.util.Collection;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.routemaster.api.auth.domain.user.email.impl.service.EmailUserService;
import org.routemaster.api.auth.domain.user.impl.data.DefaultUserDetails;
import org.routemaster.api.auth.domain.user.impl.util.constant.UserType;
import org.routemaster.api.auth.domain.user.jwt.impl.data.UserJwtPayload;
import org.routemaster.api.auth.domain.user.jwt.impl.service.UserJwtService;
import org.routemaster.api.auth.domain.user.social.impl.service.SocialUserService;
import org.routemaster.api.auth.endpoint.user.email.impl.service.EmailUserEndpointService;
import org.routemaster.api.auth.endpoint.user.impl.service.ClientUserEndpointService;
import org.routemaster.api.auth.endpoint.user.impl.service.UserEndpointService;
import org.routemaster.api.auth.endpoint.user.impl.vo.UserRequest;
import org.routemaster.api.auth.endpoint.user.impl.vo.UserResponse;
import org.routemaster.api.auth.endpoint.user.social.service.SocialUserEndpointService;
import org.routemaster.sdk.exception.data.roe.ROEFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class DefaultClientUserEndpointService implements ClientUserEndpointService {

    private final UserJwtService userJwtService;
    private final EmailUserService emailUserService;
    private final SocialUserService socialUserService;

    private final ROEFactory roeFactory;

    @Value("${authorizationserver.client.default.client-id}")
    private String clientId;

    @Value("${authorizationserver.client.default.client-secret}")
    private String clientSecret;
    @Override
    public UserResponse details(UserRequest request) {
        UserJwtPayload userJwtPayload = userJwtService.getPayload(request.getAccessToken());

        if (!clientId.equals(request.getClientId()) || !clientSecret.equals(request.getClientSecret())) {
            throw roeFactory.get("ROE103", "Invalid Client", HttpStatus.UNAUTHORIZED);
        }

        if (userJwtPayload.getUserType().equals(UserType.SOCIAL_USER)) {
            DefaultUserDetails userDetails = socialUserService.details(userJwtPayload.getTypeUserId());
            return UserResponse.builder()
                .userType(UserType.SOCIAL_USER)
                .baseUserId(userJwtPayload.getBaseUserId())
                .typeUserId(userJwtPayload.getTypeUserId())
                .authorities(userDetails.getAuthorities())
                .build();
        }
        else if (userJwtPayload.getUserType().equals(UserType.EMAIL_USER)) {
            DefaultUserDetails userDetails = emailUserService.details(userJwtPayload.getTypeUserId());
            return UserResponse.builder()
                .userType(UserType.EMAIL_USER)
                .baseUserId(userJwtPayload.getBaseUserId())
                .typeUserId(userJwtPayload.getTypeUserId())
                .authorities(userDetails.getAuthorities())
                .build();
        }

        return null;
    }
}
