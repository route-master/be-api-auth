package org.routemaster.api.auth.domain.user.data;

import org.springframework.security.core.userdetails.UserDetails;

public interface DefaultUserDetails extends UserDetails {

    String getType();
}
