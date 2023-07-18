package org.routemaster.api.auth.domain.user.info.profile.impl.service;

import java.util.List;
import org.routemaster.api.auth.domain.user.info.profile.impl.data.UserProfile;

public interface SecuredUserProfileService {

    List<UserProfile> list(List<String> ids);
    List<UserProfile> listByBaseUserId(List<String> baseUserIds);
    UserProfile details(String id);
    UserProfile detailsByBaseUserId(String baseUserId);
}
