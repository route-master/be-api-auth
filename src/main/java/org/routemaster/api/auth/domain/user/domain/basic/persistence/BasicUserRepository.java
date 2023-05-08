package org.routemaster.api.auth.domain.user.domain.basic.persistence;

import org.routemaster.api.auth.domain.user.domain.basic.data.BasicUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BasicUserRepository extends MongoRepository<BasicUser, Long> {
}
