package org.routemaster.api.auth.domain.user.info.setting.impl.persistence;

import java.util.Optional;
import org.routemaster.api.auth.domain.user.info.setting.impl.data.UserSetting;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserSettingRepository extends MongoRepository<UserSetting, String> {
    Optional<UserSetting> findByBaseUserId(String baseUserId);
    Boolean existsByBaseUserId(String baseUserId);
    void deleteByBaseUserId(String baseUserId);
}
