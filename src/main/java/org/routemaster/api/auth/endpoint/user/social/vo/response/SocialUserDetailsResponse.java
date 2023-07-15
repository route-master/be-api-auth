package org.routemaster.api.auth.endpoint.user.social.vo.response;

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
public class SocialUserDetailsResponse {

    private Oauth2UserInfo userInfo;
}
