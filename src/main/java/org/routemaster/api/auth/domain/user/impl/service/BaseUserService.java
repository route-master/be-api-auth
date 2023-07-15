package org.routemaster.api.auth.domain.user.impl.service;

import org.routemaster.api.auth.domain.user.impl.data.BaseUser;
import org.routemaster.api.auth.domain.user.impl.util.constant.UserType;

public interface BaseUserService {
    BaseUser details(UserType type, String userId);
    BaseUser details(String id);
    BaseUser save(UserType type, String id);
    BaseUser save(BaseUser baseUser);

    BaseUser merge(String standard, String other);
    BaseUser merge(BaseUser standard, BaseUser other);
    void delete(String id);
}
