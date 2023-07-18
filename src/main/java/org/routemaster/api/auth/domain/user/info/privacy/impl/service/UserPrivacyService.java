package org.routemaster.api.auth.domain.user.info.privacy.impl.service;

import java.util.List;
import org.routemaster.api.auth.domain.user.info.privacy.impl.data.UserPrivacy;

public interface UserPrivacyService {

    UserPrivacy details(String id);
    UserPrivacy details(String baseUserId, String privacyGroupId);
    List<UserPrivacy> list(String baseUserId);
    UserPrivacy save(UserPrivacy userPrivacy);
    List<UserPrivacy> saveAll(Iterable<UserPrivacy> userPrivacies);
    void delete(String id);
    void deleteAll(Iterable<String> ids);
    void deleteAllByBaseUserId(String baseUserId);
}
