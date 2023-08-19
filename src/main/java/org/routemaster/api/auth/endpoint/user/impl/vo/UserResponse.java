package org.routemaster.api.auth.endpoint.user.impl.vo;

import java.util.Collection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.routemaster.api.auth.domain.user.impl.util.constant.UserType;
import org.springframework.security.core.GrantedAuthority;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class UserResponse {

    private UserType userType;
    private String baseUserId;
    private String typeUserId;
    private Collection<? extends GrantedAuthority> authorities;
}
