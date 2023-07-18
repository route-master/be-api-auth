package org.routemaster.api.auth.endpoint.user.email.impl.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.routemaster.api.auth.domain.user.jwt.impl.data.UserJwtPayload;
import org.routemaster.api.auth.domain.user.jwt.impl.utils.filter.UserJwtAuthenticationFilter;
import org.routemaster.api.auth.endpoint.user.email.impl.service.EmailUserEndpointService;
import org.routemaster.api.auth.endpoint.user.email.impl.vo.request.EmailUserRegisterRequest;
import org.routemaster.api.auth.endpoint.user.email.impl.vo.request.EmailUserLoginRequest;
import org.routemaster.api.auth.endpoint.user.email.impl.vo.request.EmailUserUpdatePasswordRequest;
import org.routemaster.api.auth.endpoint.user.email.impl.vo.request.EmailUserVerificationRequest;
import org.routemaster.api.auth.endpoint.user.email.impl.vo.response.EmailUserLoginResponse;
import org.routemaster.api.auth.endpoint.user.email.impl.vo.response.EmailUserRegisterResponse;
import org.routemaster.api.auth.endpoint.user.email.impl.vo.response.EmailUserUpdatePasswordResponse;
import org.routemaster.api.auth.endpoint.user.email.impl.vo.response.EmailUserVerificationResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/user/email")
@RequiredArgsConstructor
public class EmailUserRestController {

    private final EmailUserEndpointService emailUserEndpointService;

    @Operation(summary = "회원가입", description = "Route Master 자체 사용자 회원가입")
    @PostMapping("/register")
    @PreAuthorize("permitAll()")
    public ResponseEntity<EmailUserRegisterResponse> register(@RequestBody @Validated EmailUserRegisterRequest request) {
        EmailUserRegisterResponse response = emailUserEndpointService.register(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "회원가입 코드 검증", description = "Route Master 자체 사용자 회원가입 후 이메일 등으로 받은 코드 검증")
    @PostMapping("/register/verification")
    @PreAuthorize("permitAll()")
    public ResponseEntity<EmailUserVerificationResponse> verifyRegister(@RequestBody @Validated EmailUserVerificationRequest request) {
        EmailUserVerificationResponse response = emailUserEndpointService.verifyRegister(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PatchMapping("/login")
    @PreAuthorize("permitAll()")
    public ResponseEntity<EmailUserLoginResponse> login(@RequestBody @Validated EmailUserLoginRequest request) {
        EmailUserLoginResponse response = emailUserEndpointService.login(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PatchMapping("/update/password")
    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<EmailUserUpdatePasswordResponse> updatePassword(
        @RequestBody @Validated EmailUserUpdatePasswordRequest request,
        @RequestAttribute(UserJwtAuthenticationFilter.USER_PAYLOAD) UserJwtPayload payload) {
        EmailUserUpdatePasswordResponse response = emailUserEndpointService.updatePassword(request, payload);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
