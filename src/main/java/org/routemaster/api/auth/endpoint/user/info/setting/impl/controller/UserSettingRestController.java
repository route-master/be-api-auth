package org.routemaster.api.auth.endpoint.user.info.setting.impl.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.routemaster.api.auth.domain.user.jwt.impl.data.UserJwtPayload;
import org.routemaster.api.auth.domain.user.jwt.impl.utils.filter.UserJwtAuthenticationFilter;
import org.routemaster.api.auth.endpoint.user.info.setting.impl.service.UserSettingEndpointService;
import org.routemaster.api.auth.endpoint.user.info.setting.impl.vo.request.UserSettingSaveRequest;
import org.routemaster.api.auth.endpoint.user.info.setting.impl.vo.response.UserSettingDeleteResponse;
import org.routemaster.api.auth.endpoint.user.info.setting.impl.vo.response.UserSettingDetailsResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/info/setting")
@RequiredArgsConstructor
public class UserSettingRestController {

    private final UserSettingEndpointService userSettingEndpointService;

    @GetMapping
    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<UserSettingDetailsResponse> details(
        @RequestAttribute(UserJwtAuthenticationFilter.USER_PAYLOAD) UserJwtPayload payload
    ) {
        UserSettingDetailsResponse response = userSettingEndpointService.details(payload);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<UserSettingDetailsResponse> save(
        @RequestAttribute(UserJwtAuthenticationFilter.USER_PAYLOAD) UserJwtPayload payload,
        @RequestBody UserSettingSaveRequest request
    ) {
        UserSettingDetailsResponse response = userSettingEndpointService.save(payload, request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

//    @DeleteMapping
//    @SecurityRequirement(name = "Bearer Authentication")
//    @PreAuthorize("hasRole('ROLE_USER')")
//    public ResponseEntity<Void> delete(
//        @RequestAttribute(UserJwtAuthenticationFilter.USER_PAYLOAD) UserJwtPayload payload
//    ) {
//        UserSettingDeleteResponse response = userSettingEndpointService.delete(payload);
//        return new ResponseEntity<>(null, HttpStatus.OK);
//    }
}
