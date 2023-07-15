package org.routemaster.api.auth.endpoint.user.info.profile.impl.vo.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class UserProfileAccessSaveRequest {

    private Boolean birthDate;
    private Boolean profileImageUrl;
    private Boolean phoneNumber;
}
