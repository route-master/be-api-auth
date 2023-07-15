package org.routemaster.api.auth.infra.oauth2.kakao.service;

import org.routemaster.api.auth.infra.oauth2.kakao.vo.KakaoOauth2UserInfo;

public interface KakaoOauth2Service {

    KakaoOauth2UserInfo requestUserInfo(String token);
    KakaoOauth2UserInfo requestUserInfo(Long targetId);
}
