package org.routemaster.api.auth.domain.user.jwt.impl.data;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import java.util.Collection;
import java.util.Set;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.routemaster.api.auth.domain.user.email.impl.data.EmailUser;
import org.routemaster.api.auth.domain.user.impl.data.BaseUser;
import org.routemaster.api.auth.domain.user.impl.util.constant.UserType;
import org.routemaster.api.auth.domain.user.jwt.impl.utils.constant.JwtType;
import org.routemaster.api.auth.domain.user.social.impl.data.SocialUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.PortResolverImpl;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
public class UserJwtPayload {

    private String baseUserId;
    private String typeUserId;
    private Set<String> authorities;
    private UserType userType;
    private JwtType jwtType;

    public static UserJwtPayload of(EmailUser user, BaseUser baseUser) {
        return UserJwtPayload.builder()
            .baseUserId(baseUser.getId())
            .typeUserId(user.getId())
            .authorities(user.getStringAuthorities())
            .userType(user.getType())
            .jwtType(JwtType.ACCESS_TOKEN)
            .build();
    }

    public static UserJwtPayload of(SocialUser user, BaseUser baseUser) {
        return UserJwtPayload.builder()
            .baseUserId(baseUser.getId())
            .typeUserId(user.getId())
            .authorities(user.getStringAuthorities())
            .userType(user.getType())
            .jwtType(JwtType.ACCESS_TOKEN)
            .build();
    }
}
