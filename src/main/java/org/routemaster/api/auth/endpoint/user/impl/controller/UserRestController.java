package org.routemaster.api.auth.endpoint.user.impl.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.routemaster.api.auth.domain.user.jwt.impl.data.UserJwtPayload;
import org.routemaster.api.auth.domain.user.jwt.impl.utils.filter.UserJwtAuthenticationFilter;
import org.routemaster.api.auth.endpoint.user.impl.service.UserEndpointService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserRestController {

    private final UserEndpointService userEndpointService;

    @PatchMapping("/logout")
    public ResponseEntity<Void> logout(
        @RequestAttribute(UserJwtAuthenticationFilter.USER_PAYLOAD) UserJwtPayload payload) {
        userEndpointService.logout(payload);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(
        @RequestAttribute(UserJwtAuthenticationFilter.USER_PAYLOAD) UserJwtPayload payload) {
        userEndpointService.delete(payload);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
