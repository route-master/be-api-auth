package org.routemaster.api.auth.endpoint.user.info.profile.impl.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.routemaster.api.auth.domain.user.info.profile.impl.data.UserProfile;
import org.routemaster.api.auth.domain.user.info.profile.impl.data.UserProfileAccess;
import org.routemaster.api.auth.domain.user.info.profile.impl.service.SecuredUserProfileService;
import org.routemaster.api.auth.domain.user.info.profile.impl.service.UserProfileAccessService;
import org.routemaster.api.auth.domain.user.info.profile.impl.service.UserProfileService;
import org.routemaster.api.auth.domain.user.jwt.impl.data.UserJwtPayload;
import org.routemaster.api.auth.domain.user.jwt.impl.data.UserJwtUnit;
import org.routemaster.api.auth.endpoint.user.info.profile.impl.service.UserProfileEndpointService;
import org.routemaster.api.auth.endpoint.user.info.profile.impl.util.mapper.UserProfileAccessMapper;
import org.routemaster.api.auth.endpoint.user.info.profile.impl.util.mapper.UserProfileMapper;
import org.routemaster.api.auth.endpoint.user.info.profile.impl.vo.request.UserProfileTotalSaveRequest;
import org.routemaster.api.auth.endpoint.user.info.profile.impl.vo.response.UserNicknameListResponse;
import org.routemaster.api.auth.endpoint.user.info.profile.impl.vo.response.UserNicknameResponse;
import org.routemaster.api.auth.endpoint.user.info.profile.impl.vo.response.UserProfileDeleteResponse;
import org.routemaster.api.auth.endpoint.user.info.profile.impl.vo.response.UserProfileDetailsResponse;
import org.routemaster.api.auth.endpoint.user.info.profile.impl.vo.response.UserProfileListResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class DefaultUserProfileEndpointService implements UserProfileEndpointService {

    private final UserProfileService userProfileService;
    private final UserProfileAccessService userProfileAccessService;
    private final SecuredUserProfileService securedUserProfileService;
    private final UserProfileMapper userProfileMapper;
    private final UserProfileAccessMapper userProfileAccessMapper;

    @Override
    public UserProfileDetailsResponse details(UserJwtPayload payload) {
        UserProfile userProfile = userProfileService.detailsByBaseUserId(payload.getBaseUserId());
        UserProfileAccess userProfileAccess = userProfileAccessService.detailsByBaseUserId(payload.getBaseUserId());
        return UserProfileDetailsResponse.builder()
            .profile(userProfile)
            .access(userProfileAccess)
            .build();
    }

    @Override
    public UserProfileDetailsResponse details(String baseUserId, UserJwtPayload payload) {
        UserProfile userProfile = securedUserProfileService.detailsByBaseUserId(baseUserId);
        UserProfileAccess userProfileAccess = userProfileAccessService.detailsByBaseUserId(payload.getBaseUserId());
        return UserProfileDetailsResponse.builder()
            .profile(userProfile)
            .access(userProfileAccess)
            .build();
    }

    @Override
    public UserProfileListResponse list(List<String> baseUserIds, UserJwtPayload payload) {
        List<UserProfile> userProfiles = securedUserProfileService.listByBaseUserId(baseUserIds);
        return UserProfileListResponse.builder()
            .profiles(userProfiles)
            .build();
    }

    @Override
    public UserNicknameListResponse nicknames(List<String> baseUserIds, UserJwtPayload payload) {
        List<UserProfile> userProfiles = securedUserProfileService.listByBaseUserId(baseUserIds);
        List<UserNicknameResponse> nicknames = new ArrayList<>();
        for (UserProfile userProfile : userProfiles) {
            nicknames.add(UserNicknameResponse.builder()
                .id(userProfile.getId())
                .baseUserId(userProfile.getBaseUserId())
                .nickname(userProfile.getNickname())
                .build());
        }
        return UserNicknameListResponse.builder()
            .nicknames(nicknames)
            .build();
    }

    @Override
    public UserNicknameResponse nickname(String baseUserId, UserJwtPayload payload) {
        UserProfile userProfile = securedUserProfileService.detailsByBaseUserId(baseUserId);
        return UserNicknameResponse.builder()
            .id(userProfile.getId())
            .baseUserId(userProfile.getBaseUserId())
            .nickname(userProfile.getNickname())
            .build();
    }

    @Override
    @Transactional
    public UserProfileDetailsResponse save(UserJwtPayload payload,
        UserProfileTotalSaveRequest request) {
        String baseUserId = payload.getBaseUserId();

        UserProfile prevProfile;
        try {
            prevProfile = userProfileService.detailsByBaseUserId(baseUserId);
        } catch (Exception e) {
            prevProfile = null;
        }

        UserProfileAccess prevAccess;
        try {
            prevAccess = userProfileAccessService.detailsByBaseUserId(baseUserId);
        } catch (Exception e) {
            prevAccess = null;
        }

        UserProfile userProfile = (prevProfile == null) ?
            userProfileMapper.save(payload, request.getProfile()) :
            userProfileMapper.save(prevProfile, request.getProfile());
        UserProfileAccess userProfileAccess = (prevAccess == null) ?
            userProfileAccessMapper.save(payload, request.getAccess()) :
            userProfileAccessMapper.save(prevAccess, request.getAccess());

        UserProfile profile = userProfileService.save(userProfile);
        UserProfileAccess access = userProfileAccessService.save(userProfileAccess);
        return UserProfileDetailsResponse.builder()
            .access(access)
            .profile(profile)
            .build();
    }

    @Override
    @Transactional
    public UserProfileDeleteResponse delete(UserJwtPayload payload) {
        userProfileService.deleteByBaseUserId(payload.getBaseUserId());
        userProfileAccessService.deleteByBaseUserId(payload.getBaseUserId());
        return UserProfileDeleteResponse.builder()
            .build();
    }

    private void validate(UserJwtPayload payload) {

    }
}
