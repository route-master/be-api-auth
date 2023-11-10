package org.routemaster.api.auth.endpoint.user.impl.service;

import org.routemaster.api.auth.domain.user.jwt.impl.data.UserJwtPayload;
import org.routemaster.api.auth.domain.user.jwt.impl.data.UserJwtUnit;

public interface UserEndpointService {

    void logout(UserJwtPayload payload);
    void delete(UserJwtPayload payload);
    UserJwtUnit refresh(UserJwtPayload payload);
}
