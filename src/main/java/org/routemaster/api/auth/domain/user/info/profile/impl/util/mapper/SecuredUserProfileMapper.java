package org.routemaster.api.auth.domain.user.info.profile.impl.util.mapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.routemaster.api.auth.domain.user.info.profile.impl.data.UserProfile;
import org.routemaster.api.auth.domain.user.info.profile.impl.data.UserProfileAccess;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class SecuredUserProfileMapper {

    public UserProfile details(UserProfile origin, UserProfileAccess access) {
        return UserProfile.builder()
            .id(origin.getId())
            .baseUserId(origin.getBaseUserId())
            .nickname(origin.getNickname())
            .birthDate(access.getBirthDate() ? origin.getBirthDate() : null)
            .profileImageUrl(access.getProfileImageUrl() ? origin.getProfileImageUrl() : null)
            .accessType(origin.getAccessType())
            .createdAt(origin.getCreatedAt())
            .updatedAt(origin.getUpdatedAt())
            .build();
    }
}
