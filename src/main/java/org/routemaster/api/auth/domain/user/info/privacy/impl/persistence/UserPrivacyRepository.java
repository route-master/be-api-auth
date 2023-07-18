package org.routemaster.api.auth.domain.user.info.privacy.impl.persistence;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.routemaster.api.auth.domain.user.info.privacy.impl.data.UserPrivacy;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserPrivacyRepository extends MongoRepository<UserPrivacy, String> {

    Optional<UserPrivacy> findByBaseUserIdAndPrivacyGroupId(String baseUserId, String privacyGroupId);
    List<UserPrivacy> findAllByBaseUserId(String baseUserId);
    Boolean existsAllById(Iterable<String> ids);
    void deleteAllByBaseUserId(String baseUserId);
}
