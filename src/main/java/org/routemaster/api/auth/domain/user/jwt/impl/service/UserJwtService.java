package org.routemaster.api.auth.domain.user.jwt.impl.service;

import org.routemaster.api.auth.domain.user.impl.data.DefaultUserDetails;
import org.routemaster.api.auth.domain.user.jwt.impl.data.UserJwtPayload;
import org.routemaster.api.auth.domain.user.jwt.impl.data.UserJwtUnit;
import org.routemaster.api.auth.global.domain.jwt.impl.vo.JwtUnit;

public interface UserJwtService {
    UserJwtUnit createTokens(DefaultUserDetails typeUser, String baseUserId);
    UserJwtUnit createAccessTokenOnly(DefaultUserDetails typeUser, String baseUserId);
    JwtUnit createAccessToken(DefaultUserDetails typeUser, String baseUserId);
    UserJwtUnit createRefreshTokenOnly(DefaultUserDetails typeUser, String baseUserId);
    JwtUnit createRefreshToken(DefaultUserDetails typeUser, String baseUserId);

    UserJwtPayload getPayload(String token);
    Boolean validateToken(String token);
}
