package org.routemaster.api.auth.domain.user.info.privacy.impl.service.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.routemaster.api.auth.domain.user.info.privacy.impl.data.Privacy;
import org.routemaster.api.auth.domain.user.info.privacy.impl.persistence.PrivacyRepository;
import org.routemaster.api.auth.domain.user.info.privacy.impl.service.PrivacyService;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class DefaultPrivacyService implements PrivacyService {

    private final PrivacyRepository privacyRepository;

    @Override
    public List<Privacy> list(Iterable<String> ids) {
        return privacyRepository.findAllById(ids);
    }

    @Override
    public List<Privacy> saveAll(List<Privacy> privacies) {
        for (Privacy p : privacies) p.setCreatedAt();
        return privacyRepository.saveAll(privacies);
    }
}
