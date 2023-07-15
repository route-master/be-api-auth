package org.routemaster.api.auth.domain.user.info.privacy.impl.service;

import java.util.List;
import org.routemaster.api.auth.domain.user.info.privacy.impl.data.Privacy;

public interface PrivacyService {
    List<Privacy> list(Iterable<String> ids);
    List<Privacy> saveAll(List<Privacy> privacies);
}
