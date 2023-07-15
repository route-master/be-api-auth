package org.routemaster.api.auth.infra.oauth2.kakao.vo;

import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.routemaster.api.auth.infra.oauth2.vo.Oauth2UserInfo;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class KakaoOauth2UserInfo implements Oauth2UserInfo {

    public static final String PROVIDER = "kakao";
    private Map<String, Object> attributes;

    @Override
    public Long getId() {
        return Long.parseLong(String.valueOf(attributes.get("id")));
    }

    @Override
    public String getProvider() {
        return PROVIDER;
    }
}
