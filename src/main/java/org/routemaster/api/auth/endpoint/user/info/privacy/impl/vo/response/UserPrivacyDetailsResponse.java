package org.routemaster.api.auth.endpoint.user.info.privacy.impl.vo.response;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.routemaster.api.auth.endpoint.privacy.impl.vo.response.PrivacyGroupResponse;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class UserPrivacyDetailsResponse {

    private String id;
    private PrivacyGroupResponse privacyGroup;
    private Boolean isApproved;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime expiresAt;
}
