package org.routemaster.api.auth.endpoint.user.info.profile.impl.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.routemaster.api.auth.domain.user.jwt.impl.data.UserJwtPayload;
import org.routemaster.api.auth.domain.user.jwt.impl.utils.filter.UserJwtAuthenticationFilter;
import org.routemaster.api.auth.endpoint.user.info.profile.impl.service.UserProfileEndpointService;
import org.routemaster.api.auth.endpoint.user.info.profile.impl.vo.request.UserProfileSaveRequest;
import org.routemaster.api.auth.endpoint.user.info.profile.impl.vo.response.UserProfileDetailsResponse;
import org.routemaster.api.auth.endpoint.user.info.profile.impl.vo.response.UserProfileListResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
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
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<UserProfileDetailsResponse> details(
        @RequestAttribute(UserJwtAuthenticationFilter.USER_PAYLOAD) UserJwtPayload payload) {
        return null;
    }

    @GetMapping("/{baseUserId}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<UserProfileDetailsResponse> details(@PathVariable Long baseUserId,
        @RequestAttribute(UserJwtAuthenticationFilter.USER_PAYLOAD) UserJwtPayload payload) {
        return null;
    }
    @GetMapping("/list")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<UserProfileListResponse> list(@RequestParam List<Long> baseUserIds,
        @RequestAttribute(UserJwtAuthenticationFilter.USER_PAYLOAD) UserJwtPayload payload) {
        return null;
    }

    @GetMapping("/nickname/list")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<UserProfileDetailsResponse> nicknames(@RequestParam List<Long> baseUserIds,
        @RequestAttribute(UserJwtAuthenticationFilter.USER_PAYLOAD) UserJwtPayload payload) {
        return null;
    }

    @GetMapping("/nickname/{baseUserId}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<UserProfileListResponse> nickname(@PathVariable Long baseUserId,
        @RequestAttribute(UserJwtAuthenticationFilter.USER_PAYLOAD) UserJwtPayload payload) {
        return null;
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<UserProfileDetailsResponse> save(
        @RequestAttribute(UserJwtAuthenticationFilter.USER_PAYLOAD) UserJwtPayload payload,
        @RequestBody UserProfileSaveRequest request) {
        return null;
    }

    @DeleteMapping()
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Void> delete() {
        return null;
    }
}
