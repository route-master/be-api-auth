package org.routemaster.api.auth.endpoint.user.social.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.routemaster.api.auth.domain.user.impl.data.BaseUser;
import org.routemaster.api.auth.domain.user.impl.exception.UserErrorCode;
import org.routemaster.api.auth.domain.user.impl.service.BaseUserService;
import org.routemaster.api.auth.domain.user.impl.util.constant.UserType;
import org.routemaster.api.auth.domain.user.jwt.impl.data.UserJwtPayload;
import org.routemaster.api.auth.domain.user.jwt.impl.data.UserJwtUnit;
import org.routemaster.api.auth.domain.user.jwt.impl.service.UserJwtService;
import org.routemaster.api.auth.domain.user.social.impl.data.SocialUser;
import org.routemaster.api.auth.domain.user.social.impl.exception.SocialUserErrorDescription;
import org.routemaster.api.auth.domain.user.social.impl.service.SocialUserService;
import org.routemaster.api.auth.endpoint.user.social.service.SocialUserEndpointService;
import org.routemaster.api.auth.endpoint.user.social.util.mapper.EndpointSocialUserMapper;
import org.routemaster.api.auth.endpoint.user.social.vo.request.SocialUserLoginRequest;
import org.routemaster.api.auth.endpoint.user.social.vo.request.SocialUserRegisterRequest;
import org.routemaster.api.auth.endpoint.user.social.vo.response.SocialUserDetailsResponse;
import org.routemaster.api.auth.endpoint.user.social.vo.response.SocialUserLoginResponse;
import org.routemaster.api.auth.endpoint.user.social.vo.response.SocialUserRegisterResponse;
import org.routemaster.api.auth.infra.oauth2.kakao.service.KakaoOauth2Service;
import org.routemaster.api.auth.infra.oauth2.kakao.vo.KakaoOauth2UserInfo;
import org.routemaster.api.auth.infra.oauth2.vo.Oauth2UserInfo;
import org.routemaster.sdk.exception.data.roe.ROEFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class DefaultSocialUserEndpointService implements SocialUserEndpointService {

    private final BaseUserService baseUserService;
    private final SocialUserService socialUserService;
    private final UserJwtService userJwtService;
    private final KakaoOauth2Service kakaoOauth2Service;
    private final EndpointSocialUserMapper socialUserMapper;
    private final ROEFactory roeFactory;

    @Override
    public SocialUserRegisterResponse register(String provider, SocialUserRegisterRequest request) {
        Oauth2UserInfo userInfo = getUserInfo(provider, request.getAccessToken());
        SocialUser socialUser = socialUserService.register(provider, userInfo.getId(), request.getAuthorities());
        baseUserService.save(UserType.SOCIAL_USER, socialUser.getId());
        return SocialUserRegisterResponse.builder()
            .id(socialUser.getId())
            .provider(socialUser.getProvider())
            .build();
    }

    @Override
    public SocialUserLoginResponse login(String provider, SocialUserLoginRequest request) {
        Oauth2UserInfo userInfo = getUserInfo(provider, request.getAccessToken());
        SocialUser socialUser = socialUserService.details(provider, userInfo.getId());
        BaseUser baseUser = baseUserService.details(UserType.SOCIAL_USER, socialUser.getId());
        UserJwtUnit tokens = userJwtService.createTokens(socialUser, baseUser.getId());
        socialUserService.updateRefreshToken(socialUser.getId(), tokens.getRefreshToken().getToken());
        return SocialUserLoginResponse.builder()
            .tokens(tokens)
            .build();
    }

    private Oauth2UserInfo getUserInfo(String provider, String accessToken) {
        switch (provider) {
            case KakaoOauth2UserInfo.PROVIDER:
                return kakaoOauth2Service.requestUserInfo(accessToken);
            default:
                throw roeFactory.get(
                    UserErrorCode.ROE_100,
                    SocialUserErrorDescription.INVALID_PROVIDER,
                    HttpStatus.BAD_REQUEST
                );
        }
    }


    @Override
    public SocialUserDetailsResponse details(UserJwtPayload payload) {
        SocialUser socialUser = socialUserService.details(payload.getTypeUserId());
        KakaoOauth2UserInfo userInfo = kakaoOauth2Service.requestUserInfo(socialUser.getSocialId());
        return SocialUserDetailsResponse.builder()
            .userInfo(userInfo)
            .build();
    }
}
