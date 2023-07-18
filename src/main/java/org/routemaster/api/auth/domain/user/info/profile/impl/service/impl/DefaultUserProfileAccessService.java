package org.routemaster.api.auth.domain.user.info.profile.impl.service.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.routemaster.api.auth.domain.user.info.impl.exception.InfoErrorCode;
import org.routemaster.api.auth.domain.user.info.profile.impl.data.UserProfileAccess;
import org.routemaster.api.auth.domain.user.info.profile.impl.exception.UserProfileErrorDescription;
import org.routemaster.api.auth.domain.user.info.profile.impl.persistence.UserProfileAccessRepository;
import org.routemaster.api.auth.domain.user.info.profile.impl.service.UserProfileAccessService;
import org.routemaster.sdk.exception.data.roe.ROEFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class DefaultUserProfileAccessService implements UserProfileAccessService {

    private final UserProfileAccessRepository userProfileAccessRepository;

    private final ROEFactory roeFactory;

    @Override
    public List<UserProfileAccess> list(List<String> id) {
        return userProfileAccessRepository.findAllById(id);
    }

    @Override
    public List<UserProfileAccess> listByBaseUserId(List<String> baseUserIds) {
        return userProfileAccessRepository.findAllByBaseUserIdIn(baseUserIds);
    }

    @Override
    public UserProfileAccess details(String id) {
        return userProfileAccessRepository.findById(id)
            .orElseThrow(() -> roeFactory.get(
                InfoErrorCode.ROE_111,
                UserProfileErrorDescription.USER_PROFILE_ACCESS_NOT_FOUND,
                HttpStatus.NOT_FOUND
            ));
    }

    @Override
    public UserProfileAccess detailsByBaseUserId(String baseUserId) {
        return userProfileAccessRepository.findByBaseUserId(baseUserId)
            .orElseThrow(() -> roeFactory.get(
                InfoErrorCode.ROE_111,
                UserProfileErrorDescription.USER_PROFILE_ACCESS_NOT_FOUND,
                HttpStatus.NOT_FOUND
            ));
    }

    @Override
    public UserProfileAccess save(UserProfileAccess userProfileAccess) {
        return userProfileAccessRepository.save(userProfileAccess);
    }

    @Override
    public void delete(String id) {
        if (!userProfileAccessRepository.existsById(id)) {
            throw roeFactory.get(
                InfoErrorCode.ROE_111,
                UserProfileErrorDescription.USER_PROFILE_ACCESS_NOT_FOUND,
                HttpStatus.NOT_FOUND
            );
        }
        userProfileAccessRepository.deleteById(id);
    }

    @Override
    public void deleteByBaseUserId(String baseUserId) {
        userProfileAccessRepository.deleteById(baseUserId);
    }
}
