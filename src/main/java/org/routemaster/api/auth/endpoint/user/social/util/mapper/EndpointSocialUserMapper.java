package org.routemaster.api.auth.endpoint.user.social.util.mapper;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.routemaster.api.auth.domain.user.social.impl.data.SocialUser;
import org.routemaster.api.auth.infra.oauth2.vo.Oauth2UserInfo;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class EndpointSocialUserMapper {

    public SocialUser register(String provider, Oauth2UserInfo userInfo) {
        return SocialUser.builder()
            .provider(provider)
            .socialId(userInfo.getId())
            .authorities(new HashSet<>(Arrays.asList("ROLE_USER")))
            .createdAt(LocalDateTime.now())
            .updatedAt(null)
            .lastActiveTime(LocalDateTime.now())
            .lockExpiredAt(null)
            .refreshToken(null)
            .build();
    }
}
