package org.routemaster.api.auth.endpoint.user.social.vo.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class SocialUserRegisterResponse {

    private String provider;
    private String id;
}
