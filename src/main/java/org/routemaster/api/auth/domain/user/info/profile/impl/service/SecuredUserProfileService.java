package org.routemaster.api.auth.domain.user.info.profile.impl.service;

import java.util.List;
import org.routemaster.api.auth.domain.user.info.profile.impl.data.UserProfile;

public interface SecuredUserProfileService {

    List<UserProfile> list(Iterable<String> ids);
    List<UserProfile> listByBaseUserId(Iterable<String> baseUserIds);
    UserProfile details(String id);
    UserProfile detailsByBaseUserId(String baseUserId);
}
