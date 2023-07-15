package org.routemaster.api.auth.endpoint.user.social.vo.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.routemaster.api.auth.domain.user.jwt.impl.data.UserJwtUnit;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class SocialUserLoginResponse {

    private UserJwtUnit tokens;
}
