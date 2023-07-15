package org.routemaster.api.auth.endpoint.user.info.privacy.impl.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.routemaster.api.auth.domain.user.info.privacy.impl.data.PrivacyGroup;
import org.routemaster.api.auth.domain.user.info.privacy.impl.data.UserPrivacy;
import org.routemaster.api.auth.domain.user.info.privacy.impl.service.PrivacyGroupService;
import org.routemaster.api.auth.domain.user.info.privacy.impl.service.UserPrivacyService;
import org.routemaster.api.auth.domain.user.jwt.impl.data.UserJwtPayload;
import org.routemaster.api.auth.endpoint.privacy.impl.service.PrivacyEndpointService;
import org.routemaster.api.auth.endpoint.privacy.impl.vo.response.PrivacyGroupListResponse;
import org.routemaster.api.auth.endpoint.privacy.impl.vo.response.PrivacyGroupResponse;
import org.routemaster.api.auth.endpoint.user.info.privacy.impl.service.UserPrivacyEndpointService;
import org.routemaster.api.auth.endpoint.user.info.privacy.impl.util.mapper.UserPrivacyMapper;
import org.routemaster.api.auth.endpoint.user.info.privacy.impl.util.mapper.UserPrivacyVOMapper;
import org.routemaster.api.auth.endpoint.user.info.privacy.impl.vo.request.UserPrivacySaveAllRequest;
import org.routemaster.api.auth.endpoint.user.info.privacy.impl.vo.request.UserPrivacySaveRequest;
import org.routemaster.api.auth.endpoint.user.info.privacy.impl.vo.response.UserPrivacyDeleteResponse;
import org.routemaster.api.auth.endpoint.user.info.privacy.impl.vo.response.UserPrivacyDetailsResponse;
import org.routemaster.api.auth.endpoint.user.info.privacy.impl.vo.response.UserPrivacyListResponse;
import org.routemaster.api.auth.endpoint.user.info.privacy.impl.vo.response.UserPrivacySaveResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class DefaultUserPrivacyEndpointService implements UserPrivacyEndpointService {

    private final PrivacyEndpointService privacyEndpointService;
    private final UserPrivacyService userPrivacyService;
    private final UserPrivacyVOMapper userPrivacyVOMapper;
    private final UserPrivacyMapper userPrivacyMapper;
    private final PrivacyGroupService privacyGroupService;

    @Override
    public UserPrivacyListResponse details(UserJwtPayload payload) {
        List<UserPrivacy> userPrivacyList = userPrivacyService.list(payload.getBaseUserId());
        PrivacyGroupListResponse response = privacyEndpointService.all();
        Map<String, PrivacyGroupResponse> privacyGroupMap = new HashMap<>();
        for (PrivacyGroupResponse privacyGroup : response.getPrivacyGroups()) {
            privacyGroupMap.put(privacyGroup.getId(), privacyGroup);
        }
        List<UserPrivacyDetailsResponse> responses =
            userPrivacyVOMapper.list(userPrivacyList, privacyGroupMap);
        return UserPrivacyListResponse.builder()
            .userPrivacies(responses)
            .build();
    }

    @Override
    @Transactional
    public UserPrivacySaveResponse save(UserJwtPayload payload, UserPrivacySaveRequest request) {
        UserPrivacy prev;
        try {
            prev = userPrivacyService.details(payload.getBaseUserId(), request.getPrivacyGroupId());
        } catch (Exception e) {
            prev = null;
        }
        PrivacyGroup privacyGroup = privacyGroupService.details(request.getPrivacyGroupId());

        UserPrivacy userPrivacy = prev == null ?
            userPrivacyMapper.save(payload, request, privacyGroup) :
            userPrivacyMapper.save(prev, request, privacyGroup);
        userPrivacyService.save(userPrivacy);

        UserPrivacyListResponse response = details(payload);
        return UserPrivacySaveResponse.builder()
            .userPrivacies(response.getUserPrivacies())
            .build();
    }

    @Override
    @Transactional
    public UserPrivacySaveResponse saveAll(UserJwtPayload payload,
        UserPrivacySaveAllRequest request) {
        List<UserPrivacy> userPrivacies = userPrivacyService.list(payload.getBaseUserId());
        Map<String, UserPrivacy> userPrivacyMap = new HashMap<>();
        for (UserPrivacy userPrivacy : userPrivacies) {
            userPrivacyMap.put(userPrivacy.getPrivacyGroupId(), userPrivacy);
        }

        List<PrivacyGroup> privacyGroups = privacyGroupService.currentPrivacyGroups();
        Map<String, PrivacyGroup> privacyGroupMap = new HashMap<>();
        for (PrivacyGroup privacyGroup : privacyGroups) {
            privacyGroupMap.put(privacyGroup.getId(), privacyGroup);
        }

        List<UserPrivacy> saves = new ArrayList<>();
        for (UserPrivacySaveRequest privacy : request.getPrivacies()) {
            String privacyGroupId = privacy.getPrivacyGroupId();
            if (userPrivacyMap.containsKey(privacyGroupId)) {
                saves.add(userPrivacyMapper.save(
                    userPrivacyMap.get(privacyGroupId), privacy, privacyGroupMap.get(privacyGroupId)));
            }
            else {
                saves.add(userPrivacyMapper.save(
                    payload, privacy, privacyGroupMap.get(privacyGroupId)));
            }
        }

        userPrivacyService.saveAll(saves);
        UserPrivacyListResponse response = details(payload);
        return UserPrivacySaveResponse.builder()
            .userPrivacies(response.getUserPrivacies())
            .build();
    }

    @Override
    @Transactional
    public UserPrivacyDeleteResponse delete(String id, UserJwtPayload payload) {
        userPrivacyService.delete(id);
        UserPrivacyListResponse response = details(payload);
        return UserPrivacyDeleteResponse.builder()
            .userPrivacies(response.getUserPrivacies())
            .build();
    }

    @Override
    @Transactional
    public UserPrivacyDeleteResponse deleteAll(List<String> ids, UserJwtPayload payload) {
        userPrivacyService.deleteAll(ids);
        UserPrivacyListResponse response = details(payload);
        return UserPrivacyDeleteResponse.builder()
            .userPrivacies(response.getUserPrivacies())
            .build();
    }
}
