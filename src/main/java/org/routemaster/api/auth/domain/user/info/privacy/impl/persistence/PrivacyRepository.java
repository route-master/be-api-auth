package org.routemaster.api.auth.domain.user.info.privacy.impl.persistence;

import java.util.List;
import org.routemaster.api.auth.domain.user.info.privacy.impl.data.Privacy;
import org.routemaster.api.auth.domain.user.info.privacy.impl.util.constant.PrivacyType;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrivacyRepository extends MongoRepository<Privacy, String> {
}
