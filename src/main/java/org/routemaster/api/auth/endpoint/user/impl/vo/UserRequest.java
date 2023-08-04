package org.routemaster.api.auth.endpoint.user.impl.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class UserRequest {

    private String clientId;
    private String clientSecret;
    private String accessToken;
}
