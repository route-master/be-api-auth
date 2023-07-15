package org.routemaster.api.auth.endpoint.user.info.privacy.impl.util.mapper;

import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.routemaster.api.auth.domain.user.info.privacy.impl.data.PrivacyGroup;
import org.routemaster.api.auth.domain.user.info.privacy.impl.data.UserPrivacy;
import org.routemaster.api.auth.domain.user.jwt.impl.data.UserJwtPayload;
import org.routemaster.api.auth.endpoint.user.info.privacy.impl.vo.request.UserPrivacySaveAllRequest;
import org.routemaster.api.auth.endpoint.user.info.privacy.impl.vo.request.UserPrivacySaveRequest;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserPrivacyMapper {

    public UserPrivacy save(UserJwtPayload payload, UserPrivacySaveRequest request, PrivacyGroup privacyGroup) {
        return UserPrivacy.builder()
            .id(null)
            .privacyGroupId(request.getPrivacyGroupId())
            .baseUserId(payload.getBaseUserId())
            .isApproved(request.getIsApproved())
            .createdAt(LocalDateTime.now())
            .updatedAt(null)
            .expiredAt(LocalDateTime.now().plusDays(privacyGroup.getExpireDays()))
            .build();
    }

    public UserPrivacy save(UserPrivacy prev, UserPrivacySaveRequest request,
        PrivacyGroup privacyGroup) {
        return UserPrivacy.builder()
            .id(prev.getId())
            .privacyGroupId(request.getPrivacyGroupId())
            .baseUserId(prev.getBaseUserId())
            .isApproved(request.getIsApproved())
            .createdAt(prev.getCreatedAt())
            .updatedAt(LocalDateTime.now())
            .expiredAt(request.getIsApproved() ?
                LocalDateTime.now().plusDays(privacyGroup.getExpireDays()) : null)
            .build();
    }
}
