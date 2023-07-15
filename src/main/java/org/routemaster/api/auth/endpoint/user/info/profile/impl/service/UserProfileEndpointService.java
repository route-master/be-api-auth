package org.routemaster.api.auth.endpoint.user.info.profile.impl.service;

import org.routemaster.api.auth.domain.user.jwt.impl.data.UserJwtPayload;
import org.routemaster.api.auth.endpoint.user.info.profile.impl.vo.request.UserProfileTotalSaveRequest;
import org.routemaster.api.auth.endpoint.user.info.profile.impl.vo.response.UserNicknameListResponse;
import org.routemaster.api.auth.endpoint.user.info.profile.impl.vo.response.UserNicknameResponse;
import org.routemaster.api.auth.endpoint.user.info.profile.impl.vo.response.UserProfileDeleteResponse;
import org.routemaster.api.auth.endpoint.user.info.profile.impl.vo.response.UserProfileDetailsResponse;
import org.routemaster.api.auth.endpoint.user.info.profile.impl.vo.response.UserProfileListResponse;

public interface UserProfileEndpointService {

    UserProfileDetailsResponse details(UserJwtPayload payload);
    UserProfileDetailsResponse details(String baseUserId, UserJwtPayload payload);
    UserProfileListResponse list(Iterable<String> baseUserIds, UserJwtPayload payload);
    UserNicknameListResponse nicknames(Iterable<String> baseUserIds, UserJwtPayload payload);
    UserNicknameResponse nickname(String baseUserId, UserJwtPayload payload);
    UserProfileDetailsResponse save(UserJwtPayload payload, UserProfileTotalSaveRequest request);
    UserProfileDeleteResponse delete(UserJwtPayload payload);
}
