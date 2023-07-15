package org.routemaster.api.auth.domain.user.info.profile.impl.service;

import java.util.List;
import org.routemaster.api.auth.domain.user.info.profile.impl.data.UserProfile;
import org.routemaster.api.auth.domain.user.info.profile.impl.data.UserProfileAccess;

public interface UserProfileAccessService {

    List<UserProfileAccess> list(Iterable<String> ids);
    List<UserProfileAccess> listByBaseUserId(Iterable<String> baseUserIds);
    UserProfileAccess details(String id);
    UserProfileAccess detailsByBaseUserId(String baseUserId);
    UserProfileAccess save(UserProfileAccess userProfileAccess);
    void delete(String id);
    void deleteByBaseUserId(String baseUserId);
}
