package org.routemaster.api.auth.domain.user.info.profile.impl.persistence;

import java.util.List;
import java.util.Optional;
import org.routemaster.api.auth.domain.user.info.profile.impl.data.UserProfile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserProfileRepository extends MongoRepository<UserProfile, String> {

    List<UserProfile> findAllByBaseUserIdIn(List<String> baseUserIds);
    Optional<UserProfile> findByBaseUserId(String baseUserId);
    Boolean existsByBaseUserId(String baseUserId);
    void deleteByBaseUserId(String baseUserId);
}
