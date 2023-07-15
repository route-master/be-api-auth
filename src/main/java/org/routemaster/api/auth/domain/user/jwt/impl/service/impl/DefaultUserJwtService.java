package org.routemaster.api.auth.domain.user.jwt.impl.service.impl;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.routemaster.api.auth.domain.user.impl.data.DefaultUserDetails;
import org.routemaster.api.auth.domain.user.jwt.impl.data.UserJwtPayload;
import org.routemaster.api.auth.domain.user.jwt.impl.data.UserJwtUnit;
import org.routemaster.api.auth.domain.user.jwt.impl.service.UserJwtService;
import org.routemaster.api.auth.domain.user.jwt.impl.utils.constant.JwtType;
import org.routemaster.api.auth.global.domain.jwt.impl.service.JwtService;
import org.routemaster.api.auth.global.domain.jwt.impl.vo.JwtUnit;
import org.routemaster.sdk.exception.data.roe.ROEFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class DefaultUserJwtService implements UserJwtService {

    private final JwtService jwtService;

    private final ROEFactory roeFactory;

    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.access-token.expires-in}")
    private Long accessTokenExpiresIn;
    @Value("${jwt.refresh-token.expires-in}")
    private Long refreshTokenExpiresIn;

    @Override
    public UserJwtUnit createTokens(DefaultUserDetails typeUser, String baseUserId) {
        return UserJwtUnit.builder()
            .accessToken(createAccessToken(typeUser, baseUserId))
            .refreshToken(createRefreshToken(typeUser, baseUserId))
            .build();
    }

    @Override
    public UserJwtUnit createAccessTokenOnly(DefaultUserDetails typeUser, String baseUserId) {
        return UserJwtUnit.builder()
            .accessToken(createAccessToken(typeUser, baseUserId))
            .refreshToken(null)
            .build();
    }

    @Override
    public JwtUnit createAccessToken(DefaultUserDetails typeUser, String baseUserId) {
        UserJwtPayload userJwtPayload = toPayload(typeUser, baseUserId, JwtType.ACCESS_TOKEN);
        String payload = new Gson().toJson(userJwtPayload, UserJwtPayload.class);
        return jwtService.createToken(payload, accessTokenExpiresIn, secret);
    }

    @Override
    public UserJwtUnit createRefreshTokenOnly(DefaultUserDetails typeUser, String baseUserId) {
        return UserJwtUnit.builder()
            .accessToken(null)
            .refreshToken(createRefreshToken(typeUser, baseUserId))
            .build();
    }

    @Override
    public JwtUnit createRefreshToken(DefaultUserDetails typeUser, String baseUserId) {
        UserJwtPayload userJwtPayload = toPayload(typeUser, baseUserId, JwtType.REFRESH_TOKEN);
        String payload = new Gson().toJson(userJwtPayload, UserJwtPayload.class);
        return jwtService.createToken(payload, refreshTokenExpiresIn, secret);
    }

    @Override
    public UserJwtPayload getPayload(String token) {
        String payload = jwtService.getPayload(token, secret);
        return new Gson().fromJson(payload, UserJwtPayload.class);
    }

    private UserJwtPayload toPayload(DefaultUserDetails typeUser, String baseUserId, JwtType jwtType) {
        return UserJwtPayload.builder()
            .baseUserId(baseUserId)
            .typeUserId(typeUser.getId())
            .userType(typeUser.getType())
            .jwtType(jwtType)
            .build();
    }

    @Override
    public Boolean validateToken(String token) {
        return jwtService.validateToken(token, secret);
    }
}
