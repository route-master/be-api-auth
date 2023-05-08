package org.routemaster.api.auth.domain.user.domain.basic.service;

import org.routemaster.api.auth.domain.user.domain.basic.data.BasicUser;
import org.routemaster.api.auth.domain.user.domain.basic.data.BasicUserReady;

import java.util.List;

public interface BasicUserAdminCommandService {

    BasicUser lockBasicUser(Long id);
    BasicUser lockBasicUser(String username);
    List<BasicUser> lockAllBasicUser(Iterable<Long> ids);
    BasicUser unlockBasicUser(Long id);
    BasicUser unlockBasicUser(String username);
    List<BasicUser> unlockAllBasicUser(Iterable<Long> ids);
    void deleteBasicUserReady(Long id);
    void deleteBasicUserReady(String username);
    void deleteAllBasicUserReady(Iterable<Long> ids);
}
