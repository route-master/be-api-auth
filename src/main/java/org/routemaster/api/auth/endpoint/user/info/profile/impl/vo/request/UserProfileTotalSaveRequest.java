package org.routemaster.api.auth.endpoint.user.info.profile.impl.vo.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class UserProfileTotalSaveRequest {

    private UserProfileSaveRequest profile;
    private UserProfileAccessSaveRequest access;
}
