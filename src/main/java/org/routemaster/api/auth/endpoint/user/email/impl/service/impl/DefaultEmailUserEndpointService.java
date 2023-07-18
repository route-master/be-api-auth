package org.routemaster.api.auth.endpoint.user.email.impl.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.routemaster.api.auth.domain.user.email.impl.data.EmailUser;
import org.routemaster.api.auth.domain.user.email.impl.data.EmailUserReady;
import org.routemaster.api.auth.domain.user.email.impl.exception.EmailUserErrorDescription;
import org.routemaster.api.auth.domain.user.email.impl.service.EmailUserReadyService;
import org.routemaster.api.auth.domain.user.email.impl.service.EmailUserService;
import org.routemaster.api.auth.domain.user.impl.data.BaseUser;
import org.routemaster.api.auth.domain.user.impl.exception.UserErrorCode;
import org.routemaster.api.auth.domain.user.impl.service.BaseUserService;
import org.routemaster.api.auth.domain.user.impl.util.constant.UserType;
import org.routemaster.api.auth.domain.user.jwt.impl.data.UserJwtPayload;
import org.routemaster.api.auth.domain.user.jwt.impl.data.UserJwtUnit;
import org.routemaster.api.auth.domain.user.jwt.impl.service.UserJwtService;
import org.routemaster.api.auth.endpoint.user.email.impl.service.EmailUserEndpointService;
import org.routemaster.api.auth.endpoint.user.email.impl.util.mapper.EndpointEmailUserReadyMapper;
import org.routemaster.api.auth.endpoint.user.email.impl.vo.request.EmailUserLoginRequest;
import org.routemaster.api.auth.endpoint.user.email.impl.vo.request.EmailUserRegisterRequest;
import org.routemaster.api.auth.endpoint.user.email.impl.vo.request.EmailUserUpdatePasswordRequest;
import org.routemaster.api.auth.endpoint.user.email.impl.vo.request.EmailUserVerificationRequest;
import org.routemaster.api.auth.endpoint.user.email.impl.vo.response.EmailUserLoginResponse;
import org.routemaster.api.auth.endpoint.user.email.impl.vo.response.EmailUserRegisterResponse;
import org.routemaster.api.auth.endpoint.user.email.impl.vo.response.EmailUserUpdatePasswordResponse;
import org.routemaster.api.auth.endpoint.user.email.impl.vo.response.EmailUserVerificationResponse;
import org.routemaster.sdk.exception.data.roe.ROEFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class DefaultEmailUserEndpointService implements EmailUserEndpointService {

    private final EndpointEmailUserReadyMapper endpointEmailUserReadyMapper;

    private final UserJwtService userJwtService;
    private final BaseUserService baseUserService;
    private final EmailUserService emailUserService;
    private final EmailUserReadyService emailUserReadyService;
    private final PasswordEncoder passwordEncoder;
    private final ROEFactory roeFactory;

    @Override
    @Transactional
    public EmailUserRegisterResponse register(EmailUserRegisterRequest request) {
        EmailUserReady emailUserReady = emailUserReadyService.register(endpointEmailUserReadyMapper.register(request));

        return EmailUserRegisterResponse.builder()
            .username(emailUserReady.getUsername())
            .build();
    }

    @Override
    @Transactional
    public EmailUserVerificationResponse verifyRegister(EmailUserVerificationRequest request) {
        EmailUser emailUser = emailUserService.verifyRegister(request.getUsername(), request.getVerificationCode());
        baseUserService.save(UserType.EMAIL_USER, emailUser.getId());

        return EmailUserVerificationResponse.builder()
            .username(emailUser.getUsername())
            .build();
    }

    @Override
    @Transactional
    public EmailUserLoginResponse login(EmailUserLoginRequest request) {
        EmailUser emailUser = emailUserService.detailsByUsername(request.getUsername());
        if (!passwordEncoder.matches(request.getPassword(), emailUser.getPassword())) {
            throw roeFactory.get(
                UserErrorCode.ROE_102,
                EmailUserErrorDescription.INVALID_USERNAME_OR_PASSWORD,
                HttpStatus.FORBIDDEN
            );
        }
        BaseUser baseUser = baseUserService.details(UserType.EMAIL_USER, emailUser.getId());
        UserJwtUnit tokens = userJwtService.createTokens(emailUser, baseUser.getId());
        emailUserService.updateRefreshToken(emailUser.getId(), tokens.getRefreshToken().getToken());
        return EmailUserLoginResponse.builder()
            .tokens(tokens)
            .build();
    }

    @Override
    @Transactional
    public EmailUserUpdatePasswordResponse updatePassword(EmailUserUpdatePasswordRequest request,
        UserJwtPayload payload) {
        EmailUser emailUser = emailUserService.updatePassword(payload.getTypeUserId(), request.getPassword());
        return EmailUserUpdatePasswordResponse.builder()
            .username(emailUser.getUsername())
            .build();
    }
}
