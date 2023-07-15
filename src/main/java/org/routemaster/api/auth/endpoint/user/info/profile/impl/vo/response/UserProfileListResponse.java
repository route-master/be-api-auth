package org.routemaster.api.auth.endpoint.user.info.profile.impl.vo.response;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.routemaster.api.auth.domain.user.info.profile.impl.data.UserProfile;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class UserProfileListResponse {

    List<UserProfile> profiles;
}
