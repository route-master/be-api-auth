package org.routemaster.api.auth.infra.oauth2.kakao.service.impl;

import jakarta.validation.Valid;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.routemaster.api.auth.domain.user.impl.exception.UserErrorCode;
import org.routemaster.api.auth.domain.user.jwt.impl.exception.UserJwtErrorDescription;
import org.routemaster.api.auth.infra.oauth2.kakao.service.KakaoOauth2Service;
import org.routemaster.api.auth.infra.oauth2.kakao.vo.KakaoOauth2UserInfo;
import org.routemaster.sdk.exception.data.roe.ROEFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;

@Slf4j
@Service
@RequiredArgsConstructor
public class DefaultKakaoOauth2Service implements KakaoOauth2Service {

    @Value("${spring.security.oauth2.client.registration.kakao.client-id}")
    private String clientId;
    @Value("${spring.security.oauth2.client.provider.kakao.user-info-uri}")
    private String userInfoUri;

    private final ROEFactory roeFactory;

    @Override
    public KakaoOauth2UserInfo requestUserInfo(String token) {
        try {
            Map<String, Object> attributes = WebClient.create()
                .get()
                .uri(userInfoUri)
                .headers((httpHeaders -> httpHeaders.setBearerAuth(token)))
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {})
                .block();

            return KakaoOauth2UserInfo.builder()
                .attributes(attributes)
                .build();
        } catch (Exception e) {
            throw roeFactory.get(
                UserErrorCode.ROE_102,
                UserJwtErrorDescription.INVALID_TOKEN,
                HttpStatus.UNAUTHORIZED
            );
        }
    }

    @Override
    public KakaoOauth2UserInfo requestUserInfo(Long targetId) {
        try {
            Map<String, Object> attributes = WebClient.create()
                .get()
                .uri(userInfoUri)
                .headers((httpHeaders -> {
                    httpHeaders.add("Authorization", String.format("KakaoAK %s", clientId));
                    httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
                }))
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {})
                .block();

            return KakaoOauth2UserInfo.builder()
                .attributes(attributes)
                .build();
        } catch (Exception e) {
            throw roeFactory.get(
                UserErrorCode.ROE_102,
                UserJwtErrorDescription.INVALID_TOKEN,
                HttpStatus.UNAUTHORIZED
            );
        }
    }
}
