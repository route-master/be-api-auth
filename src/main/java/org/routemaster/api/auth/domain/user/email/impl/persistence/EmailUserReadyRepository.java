package org.routemaster.api.auth.domain.user.email.impl.persistence;

import org.routemaster.api.auth.domain.user.email.impl.data.EmailUserReady;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmailUserReadyRepository extends MongoRepository<EmailUserReady, String> {

    Boolean existsByUsername(String username);
    Optional<EmailUserReady> findByUsername(String username);
    Optional<EmailUserReady> findByUsernameAndVerificationCode(String username, String verificationCode);
    Optional<EmailUserReady> findById(Long id);
    void deleteByUsername(String username);
}
