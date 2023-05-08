package org.routemaster.api.auth.domain.user.domain.basic.persistence;

import org.routemaster.api.auth.domain.user.domain.basic.data.BasicUser;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BasicUserRepository extends ReactiveMongoRepository<BasicUser, Long> {
}
