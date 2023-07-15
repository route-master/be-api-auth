package org.routemaster.api.auth.endpoint.user.info.setting.impl.controller;

import org.routemaster.api.auth.domain.user.jwt.impl.data.UserJwtPayload;
import org.routemaster.api.auth.domain.user.jwt.impl.utils.filter.UserJwtAuthenticationFilter;
import org.routemaster.api.auth.endpoint.user.info.setting.impl.vo.response.UserSettingDeleteResponse;
import org.routemaster.api.auth.endpoint.user.info.setting.impl.vo.response.UserSettingDetailsResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/info/setting")
public class UserSettingRestController {

    @GetMapping
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<UserSettingDetailsResponse> details(
        @RequestAttribute(UserJwtAuthenticationFilter.USER_PAYLOAD) UserJwtPayload payload
    ) {
        return null;
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<UserSettingDetailsResponse> save(
        @RequestAttribute(UserJwtAuthenticationFilter.USER_PAYLOAD) UserJwtPayload payload
    ) {
        return null;
    }

    @DeleteMapping
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<UserSettingDeleteResponse> delete(
        @RequestAttribute(UserJwtAuthenticationFilter.USER_PAYLOAD) UserJwtPayload payload
    ) {
        return null;
    }
}
