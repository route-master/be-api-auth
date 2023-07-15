package org.routemaster.api.auth.domain.user.info.profile.impl.service.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.routemaster.api.auth.domain.user.info.impl.exception.InfoErrorCode;
import org.routemaster.api.auth.domain.user.info.profile.impl.data.UserProfile;
import org.routemaster.api.auth.domain.user.info.profile.impl.exception.UserProfileErrorDescription;
import org.routemaster.api.auth.domain.user.info.profile.impl.persistence.UserProfileRepository;
import org.routemaster.api.auth.domain.user.info.profile.impl.service.UserProfileService;
import org.routemaster.sdk.exception.data.roe.ROEFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class DefaultUserProfileService implements UserProfileService {

    private final UserProfileRepository userProfileRepository;

    private final ROEFactory roeFactory;

    @Override
    public List<UserProfile> list(Iterable<String> ids) {
        return userProfileRepository.findAllById(ids);
    }

    @Override
    public List<UserProfile> listByBaseUserId(Iterable<String> baseUserIds) {
        return userProfileRepository.findAllByBaseUserId(baseUserIds);
    }

    @Override
    public UserProfile details(String id) {
        return userProfileRepository.findById(id)
            .orElseThrow(() -> roeFactory.get(
                InfoErrorCode.ROE_111,
                UserProfileErrorDescription.USER_PROFILE_NOT_FOUND,
                HttpStatus.NOT_FOUND
            ));
    }

    @Override
    public UserProfile detailsByBaseUserId(String baseUserId) {
        return userProfileRepository.findByBaseUserId(baseUserId)
            .orElseThrow(() -> roeFactory.get(
                InfoErrorCode.ROE_111,
                UserProfileErrorDescription.USER_PROFILE_NOT_FOUND,
                HttpStatus.NOT_FOUND
            ));
    }

    @Override
    public UserProfile save(UserProfile userProfile) {
        return userProfileRepository.save(userProfile);
    }

    @Override
    public void delete(String id) {
        if (!userProfileRepository.existsById(id)) {
            throw roeFactory.get(
                InfoErrorCode.ROE_111,
                UserProfileErrorDescription.USER_PROFILE_NOT_FOUND,
                HttpStatus.NOT_FOUND
            );
        }
        userProfileRepository.deleteById(id);
    }

    @Override
    public void deleteByBaseUserId(String baseUserId) {
        if (!userProfileRepository.existsById(baseUserId)) {
            throw roeFactory.get(
                InfoErrorCode.ROE_111,
                UserProfileErrorDescription.USER_PROFILE_NOT_FOUND,
                HttpStatus.NOT_FOUND
            );
        }
        userProfileRepository.deleteByBaseUserId(baseUserId);
    }
}
