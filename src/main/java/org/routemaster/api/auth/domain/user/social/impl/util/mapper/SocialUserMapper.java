package org.routemaster.api.auth.domain.user.social.impl.util.mapper;

import java.time.LocalDateTime;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.routemaster.api.auth.domain.user.social.impl.data.SocialUser;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class SocialUserMapper {

    public SocialUser register(String provider, Long socialId, Set<String> authorities) {
        return SocialUser.builder()
            .provider(provider)
            .socialId(socialId)
            .authorities(authorities)
            .createdAt(LocalDateTime.now())
            .updatedAt(null)
            .lastActiveTime(LocalDateTime.now())
            .lockExpiredAt(null)
            .refreshToken(null)
            .build();
    }
}
