package org.routemaster.api.auth.endpoint.user.email.impl.service;

import org.routemaster.api.auth.domain.user.jwt.impl.data.UserJwtPayload;
import org.routemaster.api.auth.endpoint.user.email.impl.vo.request.EmailUserLoginRequest;
import org.routemaster.api.auth.endpoint.user.email.impl.vo.request.EmailUserRegisterRequest;
import org.routemaster.api.auth.endpoint.user.email.impl.vo.request.EmailUserUpdatePasswordRequest;
import org.routemaster.api.auth.endpoint.user.email.impl.vo.request.EmailUserVerificationRequest;
import org.routemaster.api.auth.endpoint.user.email.impl.vo.response.EmailUserLoginResponse;
import org.routemaster.api.auth.endpoint.user.email.impl.vo.response.EmailUserRegisterResponse;
import org.routemaster.api.auth.endpoint.user.email.impl.vo.response.EmailUserUpdatePasswordResponse;
import org.routemaster.api.auth.endpoint.user.email.impl.vo.response.EmailUserVerificationResponse;

public interface EmailUserEndpointService {

    EmailUserRegisterResponse register(EmailUserRegisterRequest request);
    EmailUserVerificationResponse verifyRegister(EmailUserVerificationRequest request);
    EmailUserLoginResponse login(EmailUserLoginRequest request);
    EmailUserUpdatePasswordResponse updatePassword(EmailUserUpdatePasswordRequest request, UserJwtPayload payload);
}
