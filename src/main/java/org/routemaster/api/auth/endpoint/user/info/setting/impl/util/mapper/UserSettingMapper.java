package org.routemaster.api.auth.endpoint.user.info.setting.impl.util.mapper;

import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.routemaster.api.auth.domain.user.info.setting.impl.data.UserSetting;
import org.routemaster.api.auth.domain.user.jwt.impl.data.UserJwtPayload;
import org.routemaster.api.auth.endpoint.user.info.setting.impl.vo.request.UserSettingSaveRequest;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserSettingMapper {

    public UserSetting save(UserSetting prev, UserSettingSaveRequest request) {
        return UserSetting.builder()
            .id(prev.getId())
            .baseUserId(prev.getBaseUserId())
            .language(request.getLanguage())
            .timeZone(request.getTimeZone())
            .createdAt(prev.getCreatedAt())
            .updatedAt(LocalDateTime.now())
            .build();
    }

    public UserSetting save(UserJwtPayload payload, UserSettingSaveRequest request) {
        return UserSetting.builder()
            .id(null)
            .baseUserId(payload.getBaseUserId())
            .language(request.getLanguage())
            .timeZone(request.getTimeZone())
            .createdAt(LocalDateTime.now())
            .updatedAt(null)
            .build();
    }
}
