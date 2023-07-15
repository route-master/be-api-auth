package org.routemaster.api.auth.domain.user.social.impl.persistence;

import java.util.Optional;
import org.routemaster.api.auth.domain.user.social.impl.data.SocialUser;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SocialUserRepository extends MongoRepository<SocialUser, String> {

    Optional<SocialUser> findByProviderAndSocialId(String provider, Long socialId);
    Boolean existsByProviderAndSocialId(String provider, Long socialId);
    void deleteByProviderAndSocialId(String provider, Long socialId);
}
