package org.routemaster.api.auth.domain.user.info.setting.impl.service;

import org.routemaster.api.auth.domain.user.info.setting.impl.data.UserSetting;

public interface UserSettingService {

    UserSetting details(String id);
    UserSetting detailsByBaseUserId(String baseUserId);
    UserSetting save(UserSetting userSetting);
    void delete(String id);
    void deleteByBaseUserId(String baseUserId);
}
