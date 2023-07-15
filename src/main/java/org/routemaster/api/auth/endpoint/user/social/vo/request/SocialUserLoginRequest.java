package org.routemaster.api.auth.endpoint.user.social.vo.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class SocialUserLoginRequest {
    private String accessToken;
}
