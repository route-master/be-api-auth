package org.routemaster.api.auth.endpoint.user.social.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.routemaster.api.auth.domain.user.jwt.impl.data.UserJwtPayload;
import org.routemaster.api.auth.domain.user.jwt.impl.data.UserJwtUnit;
import org.routemaster.api.auth.domain.user.jwt.impl.utils.filter.UserJwtAuthenticationFilter;
import org.routemaster.api.auth.endpoint.user.social.service.SocialUserEndpointService;
import org.routemaster.api.auth.endpoint.user.social.vo.request.SocialUserLoginRequest;
import org.routemaster.api.auth.endpoint.user.social.vo.request.SocialUserRegisterRequest;
import org.routemaster.api.auth.endpoint.user.social.vo.response.SocialUserDetailsResponse;
import org.routemaster.api.auth.endpoint.user.social.vo.response.SocialUserLoginResponse;
import org.routemaster.api.auth.endpoint.user.social.vo.response.SocialUserRegisterResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/user/social")
@RequiredArgsConstructor
public class SocialUserRestController {

    private final SocialUserEndpointService socialUserEndpointService;

    @PostMapping("/{provider}/register")
    @PreAuthorize("permitAll()")
    public ResponseEntity<SocialUserRegisterResponse> register(@PathVariable String provider,
        @RequestBody SocialUserRegisterRequest request) {
        SocialUserRegisterResponse response = socialUserEndpointService.register(provider, request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PatchMapping("/{provider}/login")
    @PreAuthorize("permitAll()")
    public ResponseEntity<SocialUserLoginResponse> login(@PathVariable String provider,
        @RequestBody SocialUserLoginRequest request) {
        SocialUserLoginResponse response = socialUserEndpointService.login(provider, request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

//    @GetMapping("/info")
//    @SecurityRequirement(name = "Bearer Authentication")
//    @PreAuthorize("hasRole('ROLE_USER')")
//    public ResponseEntity<SocialUserDetailsResponse> details(
//        @RequestAttribute(UserJwtAuthenticationFilter.USER_PAYLOAD) UserJwtPayload payload) {
//        SocialUserDetailsResponse details = socialUserEndpointService.details(payload);
//        return new ResponseEntity<>(details, HttpStatus.OK);
//    }
}