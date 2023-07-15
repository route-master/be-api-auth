package org.routemaster.api.auth.endpoint.user.info.setting.impl.service;

import org.routemaster.api.auth.domain.user.jwt.impl.data.UserJwtPayload;
import org.routemaster.api.auth.endpoint.user.info.setting.impl.vo.request.UserSettingSaveRequest;
import org.routemaster.api.auth.endpoint.user.info.setting.impl.vo.response.UserSettingDeleteResponse;
import org.routemaster.api.auth.endpoint.user.info.setting.impl.vo.response.UserSettingDetailsResponse;

public interface UserSettingEndpointService {

    UserSettingDetailsResponse details(UserJwtPayload payload);
    UserSettingDetailsResponse save(UserJwtPayload payload, UserSettingSaveRequest request);
    UserSettingDeleteResponse delete(UserJwtPayload payload);
}
