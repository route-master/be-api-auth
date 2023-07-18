package org.routemaster.api.auth.endpoint.user.info.privacy.impl.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.routemaster.api.auth.domain.user.jwt.impl.data.UserJwtPayload;
import org.routemaster.api.auth.domain.user.jwt.impl.utils.filter.UserJwtAuthenticationFilter;
import org.routemaster.api.auth.endpoint.user.info.privacy.impl.service.UserPrivacyEndpointService;
import org.routemaster.api.auth.endpoint.user.info.privacy.impl.vo.request.UserPrivacySaveAllRequest;
import org.routemaster.api.auth.endpoint.user.info.privacy.impl.vo.request.UserPrivacySaveRequest;
import org.routemaster.api.auth.endpoint.user.info.privacy.impl.vo.response.UserPrivacyDeleteResponse;
import org.routemaster.api.auth.endpoint.user.info.privacy.impl.vo.response.UserPrivacyDetailsResponse;
import org.routemaster.api.auth.endpoint.user.info.privacy.impl.vo.response.UserPrivacyListResponse;
import org.routemaster.api.auth.endpoint.user.info.privacy.impl.vo.response.UserPrivacySaveResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/user/info/privacy")
@RequiredArgsConstructor
public class UserPrivacyRestController {

    private final UserPrivacyEndpointService userPrivacyEndpointService;

    @GetMapping("/me")
    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<UserPrivacyListResponse> details(
        @RequestAttribute(UserJwtAuthenticationFilter.USER_PAYLOAD) UserJwtPayload payload) {
        UserPrivacyListResponse response = userPrivacyEndpointService.details(payload);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<UserPrivacySaveResponse> save(
        @RequestAttribute(UserJwtAuthenticationFilter.USER_PAYLOAD) UserJwtPayload payload,
        @RequestBody UserPrivacySaveRequest request) {
        UserPrivacySaveResponse response = userPrivacyEndpointService.save(payload, request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

//    @PostMapping("/list")
//    @SecurityRequirement(name = "Bearer Authentication")
//    @PreAuthorize("hasRole('ROLE_USER')")
//    public ResponseEntity<UserPrivacySaveResponse> saveAll(
//        @RequestAttribute(UserJwtAuthenticationFilter.USER_PAYLOAD) UserJwtPayload payload,
//        @RequestBody UserPrivacySaveAllRequest request) {
//        UserPrivacySaveResponse response = userPrivacyEndpointService.saveAll(payload, request);
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }

//    @DeleteMapping("/{id}")
//    @SecurityRequirement(name = "Bearer Authentication")
//    @PreAuthorize("hasRole('ROLE_USER')")
//    public ResponseEntity<UserPrivacyDeleteResponse> delete(@PathVariable String id,
//        @RequestAttribute(UserJwtAuthenticationFilter.USER_PAYLOAD) UserJwtPayload payload) {
//        UserPrivacyDeleteResponse response = userPrivacyEndpointService.delete(id, payload);
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }

//    @DeleteMapping("/list")
//    @SecurityRequirement(name = "Bearer Authentication")
//    @PreAuthorize("hasRole('ROLE_USER')")
//    public ResponseEntity<UserPrivacyDeleteResponse> deleteAll(@RequestParam List<String> ids,
//        @RequestAttribute(UserJwtAuthenticationFilter.USER_PAYLOAD) UserJwtPayload payload) {
//        UserPrivacyDeleteResponse response = userPrivacyEndpointService.deleteAll(ids, payload);
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }
}
