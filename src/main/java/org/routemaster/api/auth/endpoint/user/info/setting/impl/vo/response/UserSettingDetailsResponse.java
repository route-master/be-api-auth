package org.routemaster.api.auth.endpoint.user.info.setting.impl.vo.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.routemaster.api.auth.domain.user.info.setting.impl.data.UserSetting;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class UserSettingDetailsResponse {

    private UserSetting setting;
}
