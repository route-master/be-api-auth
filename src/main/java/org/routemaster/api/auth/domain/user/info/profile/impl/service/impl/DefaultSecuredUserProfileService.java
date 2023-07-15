package org.routemaster.api.auth.domain.user.info.profile.impl.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.routemaster.api.auth.domain.user.info.impl.exception.InfoErrorCode;
import org.routemaster.api.auth.domain.user.info.profile.impl.data.UserProfile;
import org.routemaster.api.auth.domain.user.info.profile.impl.data.UserProfileAccess;
import org.routemaster.api.auth.domain.user.info.profile.impl.exception.UserProfileErrorDescription;
import org.routemaster.api.auth.domain.user.info.profile.impl.service.SecuredUserProfileService;
import org.routemaster.api.auth.domain.user.info.profile.impl.service.UserProfileAccessService;
import org.routemaster.api.auth.domain.user.info.profile.impl.service.UserProfileService;
import org.routemaster.api.auth.domain.user.info.profile.impl.util.mapper.SecuredUserProfileMapper;
import org.routemaster.sdk.exception.data.roe.ROEFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class DefaultSecuredUserProfileService implements SecuredUserProfileService {

    private final SecuredUserProfileMapper securedUserProfileMapper;

    private final UserProfileAccessService userProfileAccessService;
    private final UserProfileService userProfileService;

    private final ROEFactory roeFactory;

    @Override
    public List<UserProfile> list(Iterable<String> ids) {
        List<UserProfile> origins = userProfileService.list(ids);
        Map<String, UserProfile> originMap = new HashMap<>();
        for (UserProfile origin : origins) {
            originMap.put(origin.getId(), origin);
        }

        List<UserProfileAccess> accesses = userProfileAccessService.list(ids);
        Map<String, UserProfileAccess> accessMap = new HashMap<>();
        for (UserProfileAccess access : accesses) {
            accessMap.put(access.getId(), access);
        }

        return list(ids, originMap, accessMap);
    }

    @Override
    public List<UserProfile> listByBaseUserId(Iterable<String> baseUserIds) {
        List<UserProfile> origins = userProfileService.listByBaseUserId(baseUserIds);
        Map<String, UserProfile> originMap = new HashMap<>();
        for (UserProfile origin : origins) {
            originMap.put(origin.getBaseUserId(), origin);
        }

        List<UserProfileAccess> accesses = userProfileAccessService.listByBaseUserId(baseUserIds);
        Map<String, UserProfileAccess> accessMap = new HashMap<>();
        for (UserProfileAccess access : accesses) {
            accessMap.put(access.getBaseUserId(), access);
        }

        return list(baseUserIds, originMap, accessMap);
    }

    private List<UserProfile> list(Iterable<String> ids, Map<String, UserProfile> originMap,
        Map<String, UserProfileAccess> accessMap) {
        List<UserProfile> results = new ArrayList<>();
        for (String id : ids) {

            if (!originMap.containsKey(id)) {
                throw roeFactory.get(
                    InfoErrorCode.ROE_111,
                    UserProfileErrorDescription.USER_PROFILE_NOT_FOUND,
                    HttpStatus.NOT_FOUND
                );
            }
            if (!accessMap.containsKey(id)) {
                throw roeFactory.get(
                    InfoErrorCode.ROE_111,
                    UserProfileErrorDescription.USER_PROFILE_ACCESS_NOT_FOUND,
                    HttpStatus.NOT_FOUND
                );
            }

            results.add(details(originMap.get(id), accessMap.get(id)));
        }
        return results;
    }

    @Override
    public UserProfile details(String id) {
        UserProfile origin = userProfileService.details(id);
        UserProfileAccess access = userProfileAccessService.details(id);
        return details(origin, access);
    }

    @Override
    public UserProfile detailsByBaseUserId(String baseUserId) {
        UserProfile origin = userProfileService.detailsByBaseUserId(baseUserId);
        UserProfileAccess access = userProfileAccessService.detailsByBaseUserId(baseUserId);
        return details(origin, access);
    }

    private UserProfile details(UserProfile origin, UserProfileAccess access) {
        return securedUserProfileMapper.details(origin, access);
    }
}
