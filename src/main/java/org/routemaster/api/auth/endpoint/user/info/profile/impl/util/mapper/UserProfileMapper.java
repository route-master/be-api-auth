package org.routemaster.api.auth.endpoint.user.info.profile.impl.util.mapper;

import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.routemaster.api.auth.domain.user.info.profile.impl.data.UserProfile;
import org.routemaster.api.auth.domain.user.jwt.impl.data.UserJwtPayload;
import org.routemaster.api.auth.endpoint.user.info.profile.impl.vo.request.UserProfileSaveRequest;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserProfileMapper {

    public UserProfile save(UserProfile prev, UserProfileSaveRequest request) {
        return UserProfile.builder()
            .id(prev.getId())
            .baseUserId(prev.getBaseUserId())
            .nickname(request.getNickname())
            .birthDate(request.getBirthDate())
            .profileImageUrl(request.getProfileImageUrl())
            .createdAt(prev.getCreatedAt())
            .updatedAt(LocalDateTime.now())
            .accessType(request.getAccessType())
            .build();
    }

    public UserProfile save(UserJwtPayload payload, UserProfileSaveRequest request) {
        return UserProfile.builder()
            .id(null)
            .baseUserId(payload.getBaseUserId())
            .nickname(request.getNickname())
            .birthDate(request.getBirthDate())
            .profileImageUrl(request.getProfileImageUrl())
            .createdAt(LocalDateTime.now())
            .updatedAt(null)
            .accessType(request.getAccessType())
            .build();
    }


}
