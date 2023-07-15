package org.routemaster.api.auth.endpoint.privacy.impl.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.routemaster.api.auth.domain.user.info.privacy.impl.data.Privacy;
import org.routemaster.api.auth.domain.user.info.privacy.impl.data.PrivacyGroup;
import org.routemaster.api.auth.domain.user.info.privacy.impl.service.PrivacyGroupService;
import org.routemaster.api.auth.domain.user.info.privacy.impl.service.PrivacyService;
import org.routemaster.api.auth.endpoint.privacy.impl.service.PrivacyEndpointService;
import org.routemaster.api.auth.endpoint.privacy.impl.vo.response.PrivacyGroupListResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/privacy")
@RequiredArgsConstructor
public class PrivacyRestController {

    private final PrivacyEndpointService privacyEndpointService;
    private final PrivacyService privacyService;
    private final PrivacyGroupService privacyGroupService;

    @GetMapping("/all")
    @PreAuthorize("permitAll()")
    public ResponseEntity<PrivacyGroupListResponse> list() {
        PrivacyGroupListResponse response = privacyEndpointService.all();

        if (response.isEmpty()) {
            return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/all")
    @PreAuthorize("permitAll()")
    public ResponseEntity<List<Privacy>> saveAll(@RequestBody List<Privacy> privacies) {
        List<Privacy> response = privacyService.saveAll(privacies);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/group")
    @PreAuthorize("permitAll()")
    public ResponseEntity<PrivacyGroup> saveGroup(@RequestBody PrivacyGroup privacyGroup) {
        PrivacyGroup response = privacyGroupService.save(privacyGroup);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
