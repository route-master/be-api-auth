package org.routemaster.api.auth.endpoint.privacy.impl.util.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.routemaster.api.auth.domain.user.info.privacy.impl.data.Privacy;
import org.routemaster.api.auth.domain.user.info.privacy.impl.data.PrivacyGroup;
import org.routemaster.api.auth.endpoint.privacy.impl.vo.response.PrivacyGroupResponse;
import org.routemaster.api.auth.endpoint.privacy.impl.vo.response.PrivacyGroupListResponse;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class PrivacyGroupVOMapper {

    public PrivacyGroupResponse response(PrivacyGroup privacyGroup, Map<String, Privacy> privacyMap) {
        return PrivacyGroupResponse.builder()
            .id(privacyGroup.getId())
            .name(privacyGroup.getName())
            .description(privacyGroup.getDescription())
            .privacies(privaciesOrderByPriorities(privacyGroup.getPrivacyIds(), privacyMap))
            .type(privacyGroup.getType())
            .createdAt(privacyGroup.getCreatedAt())
            .updatedAt(privacyGroup.getUpdatedAt())
            .expiresDays(privacyGroup.getExpireDays())
            .priority(privacyGroup.getPriority())
            .build();
    }

    public PrivacyGroupListResponse listResponse(List<PrivacyGroup> privacyGroups,
            Map<String, Privacy> privacyMap) {
        List<PrivacyGroupResponse> responses = new ArrayList<>();
        for (PrivacyGroup privacyGroup : privacyGroups) {
            responses.add(response(privacyGroup, privacyMap));
        }
        responses.sort((p1, p2) -> p1.getPriority().compareTo(p2.getPriority()));
        return PrivacyGroupListResponse.builder()
            .privacyGroups(responses)
            .build();
    }

    private List<Privacy> privaciesOrderByPriorities(Iterable<String> privacyIds,
        Map<String, Privacy> privacyMap) {
        List<Privacy> privacies = new ArrayList<>();
        for (String privacyId : privacyIds) {
            privacies.add(privacyMap.get(privacyId));
        }
        privacies.sort((p1, p2) -> p1.getPriority().compareTo(p2.getPriority()));
        return privacies;
    }
}
