package org.routemaster.api.auth.domain.user.domain.basic.persistence;

import org.routemaster.api.auth.domain.user.domain.basic.data.BasicUserReady;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BasicUserReadyRepository extends MongoRepository<BasicUserReady, String> {
}
