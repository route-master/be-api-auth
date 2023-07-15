package org.routemaster.api.auth.domain.user.email.impl.persistence;

import org.routemaster.api.auth.domain.user.email.impl.data.EmailUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmailUserRepository extends MongoRepository<EmailUser, String> {
    Optional<EmailUser> findByUsername(String username);
    Optional<EmailUser> findByUsernameAndPassword(String username, String password);
    Boolean existsByUsername(String username);
    void deleteByUsername(String username);
}
