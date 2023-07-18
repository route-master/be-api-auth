package org.routemaster.api.auth.domain.user.jwt.impl.data;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.routemaster.api.auth.domain.user.impl.util.constant.UserType;
import org.routemaster.api.auth.domain.user.jwt.impl.utils.constant.JwtType;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
public class UserJwtPayload {

    private String baseUserId;
    private String typeUserId;
    private UserType userType;
    private JwtType jwtType;
}
