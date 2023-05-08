package org.routemaster.api.auth.domain.user.domain.basic.service;

import org.routemaster.api.auth.domain.user.data.BaseUser;
import org.routemaster.api.auth.domain.user.domain.basic.data.BasicUser;
import org.routemaster.api.auth.domain.user.domain.basic.data.BasicUserReady;

import java.util.List;

public interface BasicUserQueryService {

    BasicUser basicUserDetails(Long id);
    BasicUser basicUserDetails(String username);
    BasicUserReady basicUserReadyDetails(Long id);
    BasicUserReady basicUserReadyDetails(String username);
}
