package org.routemaster.api.auth.domain.user.info.privacy.impl.service.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.routemaster.api.auth.domain.user.info.impl.exception.InfoErrorCode;
import org.routemaster.api.auth.domain.user.info.privacy.impl.data.UserPrivacy;
import org.routemaster.api.auth.domain.user.info.privacy.impl.exception.PrivacyErrorDescription;
import org.routemaster.api.auth.domain.user.info.privacy.impl.persistence.UserPrivacyRepository;
import org.routemaster.api.auth.domain.user.info.privacy.impl.service.UserPrivacyService;
import org.routemaster.sdk.exception.data.roe.ROEFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class DefaultUserPrivacyService implements UserPrivacyService {

    private final UserPrivacyRepository userPrivacyRepository;

    private final ROEFactory roeFactory;

    @Override
    public UserPrivacy details(String id) {
        return userPrivacyRepository.findById(id)
            .orElseThrow(() -> roeFactory.get(
                InfoErrorCode.ROE_111,
                PrivacyErrorDescription.USER_PRIVACY_NOT_FOUND,
                HttpStatus.NOT_FOUND
            ));
    }

    @Override
    public UserPrivacy details(String baseUserId, String privacyGroupId) {
        return userPrivacyRepository.findByBaseUserIdAndPrivacyGroupId(baseUserId, privacyGroupId)
            .orElseThrow(() -> roeFactory.get(
                InfoErrorCode.ROE_111,
                PrivacyErrorDescription.USER_PRIVACY_NOT_FOUND,
                HttpStatus.NOT_FOUND
            ));
    }

    @Override
    public List<UserPrivacy> list(String baseUserId) {
        return userPrivacyRepository.findAllByBaseUserId(baseUserId);
    }

    @Override
    public UserPrivacy save(UserPrivacy userPrivacy) {
        return userPrivacyRepository.save(userPrivacy);
    }

    @Override
    public List<UserPrivacy> saveAll(Iterable<UserPrivacy> userPrivacies) {
        return userPrivacyRepository.saveAll(userPrivacies);
    }

    @Override
    public void delete(String id) {
        if (!userPrivacyRepository.existsById(id)) {
            throw roeFactory.get(
                InfoErrorCode.ROE_111,
                PrivacyErrorDescription.USER_PRIVACY_NOT_FOUND,
                HttpStatus.NOT_FOUND
            );
        }
        userPrivacyRepository.deleteById(id);
    }

    @Override
    public void deleteAll(Iterable<String> ids) {
        if (!userPrivacyRepository.existsAllById(ids)) {
            throw roeFactory.get(
                InfoErrorCode.ROE_111,
                PrivacyErrorDescription.USER_PRIVACY_NOT_FOUND,
                HttpStatus.NOT_FOUND
            );
        }
        userPrivacyRepository.deleteAllById(ids);
    }
}
