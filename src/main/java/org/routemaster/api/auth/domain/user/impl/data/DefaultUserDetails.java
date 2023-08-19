package org.routemaster.api.auth.domain.user.impl.data;

import java.util.Set;
import org.routemaster.api.auth.domain.user.impl.util.constant.UserType;
import org.springframework.security.core.userdetails.UserDetails;

public interface DefaultUserDetails extends UserDetails {

    String getId();
    UserType getType();
    String getRefreshToken();
    Set<String> getStringAuthorities();
}
