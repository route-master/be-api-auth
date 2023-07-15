package org.routemaster.api.auth.endpoint.user.info.privacy.impl.vo.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class UserPrivacySaveRequest {

    private String privacyGroupId;
    private Boolean isApproved;
}