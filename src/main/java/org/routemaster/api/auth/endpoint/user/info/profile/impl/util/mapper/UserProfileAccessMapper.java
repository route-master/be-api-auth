package org.routemaster.api.auth.endpoint.user.info.profile.impl.util.mapper;

import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.routemaster.api.auth.domain.user.info.profile.impl.data.UserProfileAccess;
import org.routemaster.api.auth.domain.user.jwt.impl.data.UserJwtPayload;
import org.routemaster.api.auth.endpoint.user.info.profile.impl.vo.request.UserProfileAccessSaveRequest;
import org.routemaster.api.auth.endpoint.user.info.profile.impl.vo.request.UserProfileSaveRequest;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserProfileAccessMapper {

    public UserProfileAccess save(UserProfileAccess prev, UserProfileAccessSaveRequest request) {
        return UserProfileAccess.builder()
            .id(prev.getId())
            .baseUserId(prev.getBaseUserId())
            .birthDate(request.getBirthDate())
            .profileImageUrl(request.getProfileImageUrl())
            .createdAt(prev.getCreatedAt())
            .updatedAt(LocalDateTime.now())
            .build();
    }

    public UserProfileAccess save(UserJwtPayload payload, UserProfileAccessSaveRequest request) {
        return UserProfileAccess.builder()
            .id(null)
            .baseUserId(payload.getBaseUserId())
            .birthDate(request.getBirthDate())
            .profileImageUrl(request.getProfileImageUrl())
            .createdAt(LocalDateTime.now())
            .updatedAt(null)
            .build();
    }
}
