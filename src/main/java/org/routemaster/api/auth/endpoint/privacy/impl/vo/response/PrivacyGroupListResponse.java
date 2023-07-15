package org.routemaster.api.auth.endpoint.privacy.impl.vo.response;

import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
public class PrivacyGroupListResponse {

    private List<PrivacyGroupResponse> privacyGroups;
    public boolean isEmpty() {
        return privacyGroups.isEmpty();
    }
}
