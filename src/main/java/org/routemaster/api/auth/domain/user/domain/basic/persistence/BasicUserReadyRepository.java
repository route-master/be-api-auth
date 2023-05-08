package org.routemaster.api.auth.domain.user.domain.basic.persistence;

import org.routemaster.api.auth.domain.user.domain.basic.data.BasicUser;
import org.routemaster.api.auth.domain.user.domain.basic.data.BasicUserReady;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface BasicUserReadyRepository extends ReactiveMongoRepository<BasicUserReady, String> {

    Mono<Boolean> existsByUsername(String username);
    Mono<BasicUserReady> findByUsername(String username);
    Mono<Void> deleteAllByUsername(String username);
}
