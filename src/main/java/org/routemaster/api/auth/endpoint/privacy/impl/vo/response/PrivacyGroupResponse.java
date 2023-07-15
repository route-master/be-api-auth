package org.routemaster.api.auth.endpoint.privacy.impl.vo.response;

import java.time.LocalDateTime;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.routemaster.api.auth.domain.user.info.privacy.impl.data.Privacy;
import org.routemaster.api.auth.domain.user.info.privacy.impl.util.constant.PrivacyType;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
public class PrivacyGroupResponse {

    private String id;
    private String name;
    private String description;
    private List<Privacy> privacies;
    private PrivacyType type;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long expiresDays;
    private Long priority;
}
