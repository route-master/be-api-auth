package org.routemaster.api.auth.domain.user.social.impl.service;

import java.util.Set;
import org.routemaster.api.auth.domain.user.social.impl.data.SocialUser;

public interface SocialUserService {

    SocialUser details(String id);
    SocialUser details(String provider, Long socialId);
    SocialUser register(String provider, Long socialId, Set<String> authorities);
    SocialUser updateRefreshToken(String id, String refreshToken);
    SocialUser updateRefreshToken(String provider, Long socialId, String refreshToken);
    void delete(String id);
    void delete(String provider, Long socialId);
}
