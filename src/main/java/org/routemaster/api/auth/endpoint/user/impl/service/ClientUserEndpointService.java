package org.routemaster.api.auth.endpoint.user.impl.service;

import java.util.Collection;
import org.routemaster.api.auth.domain.user.impl.data.DefaultUserDetails;
import org.routemaster.api.auth.endpoint.user.impl.vo.UserRequest;
import org.routemaster.api.auth.endpoint.user.impl.vo.UserResponse;
import org.springframework.security.core.GrantedAuthority;

public interface ClientUserEndpointService {

    UserResponse details(UserRequest request);
}
