package org.routemaster.api.auth.domain.user.info.profile.impl.persistence;

import java.util.List;
import java.util.Optional;
import org.routemaster.api.auth.domain.user.info.profile.impl.data.UserProfileAccess;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserProfileAccessRepository extends MongoRepository<UserProfileAccess, String> {

    List<UserProfileAccess> findAllByBaseUserId(Iterable<String> baseUserIds);
    Optional<UserProfileAccess> findByBaseUserId(String baseUserId);
    Boolean existsByBaseUserId(String baseUserId);
}
