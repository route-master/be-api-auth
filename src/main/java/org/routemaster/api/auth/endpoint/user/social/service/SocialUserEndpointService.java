package org.routemaster.api.auth.endpoint.user.social.service;

import java.util.Map;
import org.routemaster.api.auth.domain.user.jwt.impl.data.UserJwtPayload;
import org.routemaster.api.auth.domain.user.jwt.impl.data.UserJwtUnit;
import org.routemaster.api.auth.endpoint.user.social.vo.request.SocialUserLoginRequest;
import org.routemaster.api.auth.endpoint.user.social.vo.request.SocialUserRegisterRequest;
import org.routemaster.api.auth.endpoint.user.social.vo.response.SocialUserDetailsResponse;
import org.routemaster.api.auth.endpoint.user.social.vo.response.SocialUserLoginResponse;
import org.routemaster.api.auth.endpoint.user.social.vo.response.SocialUserRegisterResponse;

public interface SocialUserEndpointService {

    SocialUserRegisterResponse register(String provider, SocialUserRegisterRequest request);
    SocialUserLoginResponse login(String provider, SocialUserLoginRequest request);
    SocialUserDetailsResponse details(UserJwtPayload payload);
}
