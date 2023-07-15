package org.routemaster.api.auth.endpoint.user.info.privacy.impl.util.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.routemaster.api.auth.domain.user.info.privacy.impl.data.UserPrivacy;
import org.routemaster.api.auth.endpoint.privacy.impl.vo.response.PrivacyGroupResponse;
import org.routemaster.api.auth.endpoint.user.info.privacy.impl.vo.response.UserPrivacyDetailsResponse;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UserPrivacyVOMapper {

    public List<UserPrivacyDetailsResponse> list(List<UserPrivacy> userPrivacies, Map<String, PrivacyGroupResponse> privacyGroupMap) {
        List<UserPrivacyDetailsResponse> responses = new ArrayList<>();
        for (UserPrivacy userPrivacy : userPrivacies) {
            responses.add(UserPrivacyDetailsResponse.builder()
                    .id(userPrivacy.getId())
                    .privacyGroup(privacyGroupMap.get(userPrivacy.getPrivacyGroupId()))
                    .isApproved(userPrivacy.getIsApproved())
                    .createdAt(userPrivacy.getCreatedAt())
                    .updatedAt(userPrivacy.getUpdatedAt())
                    .expiresAt(userPrivacy.getExpiredAt())
                .build());
        }
        responses.sort((p1, p2) ->
            p1.getPrivacyGroup().getPriority().compareTo(p2.getPrivacyGroup().getPriority()));
        return responses;
    }
}
