package org.routemaster.api.auth.endpoint.user.social.vo.request;

import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class SocialUserRegisterRequest {

    private String accessToken;
    private Set<String> authorities;
}
