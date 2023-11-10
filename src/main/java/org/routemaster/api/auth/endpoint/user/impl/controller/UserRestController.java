package org.routemaster.api.auth.endpoint.user.impl.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import java.util.Collection;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.routemaster.api.auth.domain.user.impl.data.DefaultUserDetails;
import org.routemaster.api.auth.domain.user.jwt.impl.data.UserJwtPayload;
import org.routemaster.api.auth.domain.user.jwt.impl.data.UserJwtUnit;
import org.routemaster.api.auth.domain.user.jwt.impl.utils.filter.UserJwtAuthenticationFilter;
import org.routemaster.api.auth.endpoint.user.impl.service.ClientUserEndpointService;
import org.routemaster.api.auth.endpoint.user.impl.service.UserEndpointService;
import org.routemaster.api.auth.endpoint.user.impl.vo.UserRequest;
import org.routemaster.api.auth.endpoint.user.impl.vo.UserResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserRestController {

    private final UserEndpointService userEndpointService;
    private final ClientUserEndpointService clientUserEndpointService;

    @PatchMapping("/logout")
    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Void> logout(
        @RequestAttribute(UserJwtAuthenticationFilter.USER_PAYLOAD) UserJwtPayload payload) {
        userEndpointService.logout(payload);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/refresh")
    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<UserJwtUnit> refresh(
        @RequestAttribute(UserJwtAuthenticationFilter.USER_PAYLOAD) UserJwtPayload payload) {
        UserJwtUnit unit = userEndpointService.refresh(payload);
        return new ResponseEntity<>(unit, HttpStatus.OK);
    }

    @DeleteMapping
    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<UserJwtUnit> delete(
        @RequestAttribute(UserJwtAuthenticationFilter.USER_PAYLOAD) UserJwtPayload payload) {
        userEndpointService.delete(payload);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @PostMapping("/authorities")
    public ResponseEntity<UserResponse> details(@RequestBody UserRequest request) {
        return new ResponseEntity<>(clientUserEndpointService.details(request) ,HttpStatus.OK);
    }
}
