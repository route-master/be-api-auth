package org.routemaster.api.auth.endpoint.user.info.setting.impl.vo.request;

import java.util.TimeZone;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.routemaster.api.auth.domain.user.info.setting.impl.util.constant.NaturalLanguage;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class UserSettingSaveRequest {

    private NaturalLanguage language;
    private String timeZone;
}
