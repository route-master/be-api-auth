package org.routemaster.api.auth.domain.user.impl.persistence;

import java.util.List;
import java.util.Optional;
import org.routemaster.api.auth.domain.user.impl.data.BaseUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BaseUserRepository extends MongoRepository<BaseUser, String> {

    Optional<BaseUser> findByEmailUsersIn(List<String> emailUserIds);
    Optional<BaseUser> findBySocialUsersIn(List<String> socialUserIds);
}
