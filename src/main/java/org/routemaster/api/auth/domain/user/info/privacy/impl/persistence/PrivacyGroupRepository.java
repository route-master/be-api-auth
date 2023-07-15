package org.routemaster.api.auth.domain.user.info.privacy.impl.persistence;

import java.util.List;
import org.routemaster.api.auth.domain.user.info.privacy.impl.data.PrivacyGroup;
import org.routemaster.api.auth.domain.user.info.privacy.impl.util.constant.PrivacyType;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PrivacyGroupRepository extends MongoRepository<PrivacyGroup, String> {

    List<PrivacyGroup> findAllByTypeIn(Iterable<PrivacyType> types);
}
