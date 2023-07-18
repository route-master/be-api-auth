package org.routemaster.api.auth.endpoint.user.info.profile.impl.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import java.util.List;
import java.util.TimeZone;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.routemaster.api.auth.domain.user.jwt.impl.data.UserJwtPayload;
import org.routemaster.api.auth.domain.user.jwt.impl.utils.filter.UserJwtAuthenticationFilter;
import org.routemaster.api.auth.endpoint.user.info.profile.impl.service.UserProfileEndpointService;
import org.routemaster.api.auth.endpoint.user.info.profile.impl.vo.request.UserProfileSaveRequest;
import org.routemaster.api.auth.endpoint.user.info.profile.impl.vo.request.UserProfileTotalSaveRequest;
import org.routemaster.api.auth.endpoint.user.info.profile.impl.vo.response.UserNicknameListResponse;
import org.routemaster.api.auth.endpoint.user.info.profile.impl.vo.response.UserNicknameResponse;
import org.routemaster.api.auth.endpoint.user.info.profile.impl.vo.response.UserProfileDeleteResponse;
import org.routemaster.api.auth.endpoint.user.info.profile.impl.vo.response.UserProfileDetailsResponse;
import org.routemaster.api.auth.endpoint.user.info.profile.impl.vo.response.UserProfileListResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
@RequestMapping("/user/info/profile")
@RequiredArgsConstructor
public class UserProfileRestController {

    private final UserProfileEndpointService userProfileEndpointService;

    @GetMapping("/me")
    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<UserProfileDetailsResponse> details(
        @RequestAttribute(UserJwtAuthenticationFilter.USER_PAYLOAD) UserJwtPayload payload) {
        UserProfileDetailsResponse response = userProfileEndpointService.details(payload);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{baseUserId}")
    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<UserProfileDetailsResponse> details(@PathVariable String baseUserId,
        @RequestAttribute(UserJwtAuthenticationFilter.USER_PAYLOAD) UserJwtPayload payload) {
        UserProfileDetailsResponse response = userProfileEndpointService.details(baseUserId, payload);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/list")
    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<UserProfileListResponse> list(@RequestParam List<String> baseUserIds,
        @RequestAttribute(UserJwtAuthenticationFilter.USER_PAYLOAD) UserJwtPayload payload) {
        UserProfileListResponse response = userProfileEndpointService.list(baseUserIds, payload);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/nickname/list")
    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<UserNicknameListResponse> nicknames(@RequestParam List<String> baseUserIds,
        @RequestAttribute(UserJwtAuthenticationFilter.USER_PAYLOAD) UserJwtPayload payload) {
        UserNicknameListResponse response = userProfileEndpointService.nicknames(baseUserIds, payload);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/nickname/{baseUserId}")
    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<UserNicknameResponse> nickname(@PathVariable String baseUserId,
        @RequestAttribute(UserJwtAuthenticationFilter.USER_PAYLOAD) UserJwtPayload payload) {
        UserNicknameResponse response = userProfileEndpointService.nickname(baseUserId, payload);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<UserProfileDetailsResponse> save(
        @RequestAttribute(UserJwtAuthenticationFilter.USER_PAYLOAD) UserJwtPayload payload,
        @RequestBody UserProfileTotalSaveRequest request) {
        UserProfileDetailsResponse response = userProfileEndpointService.save(payload, request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

//    @DeleteMapping
//    @SecurityRequirement(name = "Bearer Authentication")
//    @PreAuthorize("hasRole('ROLE_USER')")
//    public ResponseEntity<Void> delete(
//        @RequestAttribute(UserJwtAuthenticationFilter.USER_PAYLOAD) UserJwtPayload payload
//    ) {
//        UserProfileDeleteResponse response = userProfileEndpointService.delete(payload);
//        return new ResponseEntity<>(null, HttpStatus.OK);
//    }
}
