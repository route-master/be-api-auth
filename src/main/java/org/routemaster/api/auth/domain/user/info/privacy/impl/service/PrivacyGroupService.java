package org.routemaster.api.auth.domain.user.info.privacy.impl.service;

import java.util.List;
import org.routemaster.api.auth.domain.user.info.privacy.impl.data.PrivacyGroup;

public interface PrivacyGroupService {

    PrivacyGroup details(String id);
    List<PrivacyGroup> currentPrivacyGroups();
    PrivacyGroup save(PrivacyGroup privacyGroup);
    List<PrivacyGroup> saveAll(Iterable<PrivacyGroup> privacyGroups);
}