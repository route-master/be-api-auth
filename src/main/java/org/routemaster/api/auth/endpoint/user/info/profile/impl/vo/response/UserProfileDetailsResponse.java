package org.routemaster.api.auth.endpoint.user.info.profile.impl.vo.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.routemaster.api.auth.domain.user.info.profile.impl.data.UserProfile;
import org.routemaster.api.auth.domain.user.info.profile.impl.data.UserProfileAccess;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class UserProfileDetailsResponse {

    private UserProfile profile;
    private UserProfileAccess access;
}
