package org.routemaster.api.auth.domain.user.info.privacy.impl.service.impl;

import java.util.Arrays;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.routemaster.api.auth.domain.user.info.impl.exception.InfoErrorCode;
import org.routemaster.api.auth.domain.user.info.privacy.impl.data.PrivacyGroup;
import org.routemaster.api.auth.domain.user.info.privacy.impl.exception.PrivacyErrorDescription;
import org.routemaster.api.auth.domain.user.info.privacy.impl.persistence.PrivacyGroupRepository;
import org.routemaster.api.auth.domain.user.info.privacy.impl.service.PrivacyGroupService;
import org.routemaster.api.auth.domain.user.info.privacy.impl.util.constant.PrivacyType;
import org.routemaster.sdk.exception.data.roe.ROEFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class DefaultPrivacyGroupService implements PrivacyGroupService {

    private final PrivacyGroupRepository privacyGroupRepository;
    private final ROEFactory roeFactory;

    @Override
    public PrivacyGroup details(String id) {
        return privacyGroupRepository.findById(id)
            .orElseThrow(() -> roeFactory.get(
                InfoErrorCode.ROE_111,
                PrivacyErrorDescription.PRIVACY_GROUP_NOT_FOUND,
                HttpStatus.NOT_FOUND
            ));
    }

    @Override
    public List<PrivacyGroup> currentPrivacyGroups() {
        return privacyGroupRepository.findAllByTypeIn(Arrays.asList(
            PrivacyType.INDEX,
            PrivacyType.REQUIRED,
            PrivacyType.OPTIONAL
        ));
    }

    @Override
    public PrivacyGroup save(PrivacyGroup privacyGroup) {
        return privacyGroupRepository.save(privacyGroup);
    }

    @Override
    public List<PrivacyGroup> saveAll(Iterable<PrivacyGroup> privacyGroups) {
        return privacyGroupRepository.saveAll(privacyGroups);
    }
}
