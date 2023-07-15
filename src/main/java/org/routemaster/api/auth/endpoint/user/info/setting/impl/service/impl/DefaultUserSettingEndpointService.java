package org.routemaster.api.auth.endpoint.user.info.setting.impl.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.routemaster.api.auth.domain.user.info.setting.impl.data.UserSetting;
import org.routemaster.api.auth.domain.user.info.setting.impl.service.UserSettingService;
import org.routemaster.api.auth.domain.user.jwt.impl.data.UserJwtPayload;
import org.routemaster.api.auth.endpoint.user.info.setting.impl.service.UserSettingEndpointService;
import org.routemaster.api.auth.endpoint.user.info.setting.impl.util.mapper.UserSettingMapper;
import org.routemaster.api.auth.endpoint.user.info.setting.impl.vo.request.UserSettingSaveRequest;
import org.routemaster.api.auth.endpoint.user.info.setting.impl.vo.response.UserSettingDeleteResponse;
import org.routemaster.api.auth.endpoint.user.info.setting.impl.vo.response.UserSettingDetailsResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class DefaultUserSettingEndpointService implements UserSettingEndpointService {

    private final UserSettingService userSettingService;
    private final UserSettingMapper userSettingMapper;

    @Override
    public UserSettingDetailsResponse details(UserJwtPayload payload) {
        UserSetting userSetting = userSettingService.detailsByBaseUserId(payload.getBaseUserId());
        return UserSettingDetailsResponse.builder()
            .setting(userSetting)
            .build();
    }

    @Override
    @Transactional
    public UserSettingDetailsResponse save(UserJwtPayload payload, UserSettingSaveRequest request) {
        UserSetting prev;
        try {
            prev = userSettingService.detailsByBaseUserId(payload.getBaseUserId());
        } catch (Exception e) {
            prev = null;
        }

        UserSetting userSetting = (prev == null) ?
            userSettingMapper.save(payload, request) :
            userSettingMapper.save(prev, request);

        UserSetting setting = userSettingService.save(userSetting);
        return UserSettingDetailsResponse.builder()
            .setting(setting)
            .build();
    }

    @Override
    @Transactional
    public UserSettingDeleteResponse delete(UserJwtPayload payload) {
        userSettingService.deleteByBaseUserId(payload.getBaseUserId());
        return UserSettingDeleteResponse.builder()
            .build();
    }
}
