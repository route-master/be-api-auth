package org.routemaster.api.auth.endpoint.privacy.impl.service.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.routemaster.api.auth.domain.user.info.impl.exception.InfoErrorCode;
import org.routemaster.api.auth.domain.user.info.privacy.impl.data.Privacy;
import org.routemaster.api.auth.domain.user.info.privacy.impl.data.PrivacyGroup;
import org.routemaster.api.auth.domain.user.info.privacy.impl.exception.PrivacyErrorDescription;
import org.routemaster.api.auth.domain.user.info.privacy.impl.service.PrivacyGroupService;
import org.routemaster.api.auth.domain.user.info.privacy.impl.service.PrivacyService;
import org.routemaster.api.auth.endpoint.privacy.impl.service.PrivacyEndpointService;
import org.routemaster.api.auth.endpoint.privacy.impl.util.mapper.PrivacyGroupVOMapper;
import org.routemaster.api.auth.endpoint.privacy.impl.vo.response.PrivacyGroupListResponse;
import org.routemaster.sdk.exception.data.roe.ROEFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class DefaultPrivacyEndpointService implements PrivacyEndpointService {

    private final PrivacyService privacyService;
    private final PrivacyGroupService privacyGroupService;
    private final PrivacyGroupVOMapper privacyGroupVOMapper;

    private final ROEFactory roeFactory;

    @Override
    public PrivacyGroupListResponse all() {
        List<PrivacyGroup> privacyGroups = privacyGroupService.currentPrivacyGroups();

        Set<String> privacyIds = new HashSet<>();
        for (PrivacyGroup privacyGroup : privacyGroups) {
            privacyIds.addAll(privacyGroup.getPrivacyIds());
        }

        List<Privacy> privacies = privacyService.list(privacyIds);
        if (privacyIds.size() != privacies.size()) {
            roeFactory.get(
                InfoErrorCode.ROE_111,
                PrivacyErrorDescription.PRIVACY_NOT_FOUND,
                HttpStatus.NOT_FOUND
            );
        }
        Map<String, Privacy> privacyMap = new HashMap<>();
        for (Privacy privacy : privacies) {
            privacyMap.put(privacy.getId(), privacy);
        }

        return privacyGroupVOMapper.listResponse(privacyGroups, privacyMap);
    }
}
