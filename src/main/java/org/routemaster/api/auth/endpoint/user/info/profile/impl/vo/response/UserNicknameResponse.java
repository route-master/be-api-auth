package org.routemaster.api.auth.endpoint.user.info.profile.impl.vo.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class UserNicknameResponse {

    private String id;
    private String baseUserId;
    private String nickname;
}
