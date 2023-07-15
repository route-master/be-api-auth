package org.routemaster.api.auth.endpoint.user.info.privacy.impl.service;

import java.util.List;
import org.routemaster.api.auth.domain.user.jwt.impl.data.UserJwtPayload;
import org.routemaster.api.auth.endpoint.user.info.privacy.impl.vo.request.UserPrivacySaveAllRequest;
import org.routemaster.api.auth.endpoint.user.info.privacy.impl.vo.request.UserPrivacySaveRequest;
import org.routemaster.api.auth.endpoint.user.info.privacy.impl.vo.response.UserPrivacyDeleteResponse;
import org.routemaster.api.auth.endpoint.user.info.privacy.impl.vo.response.UserPrivacyDetailsResponse;
import org.routemaster.api.auth.endpoint.user.info.privacy.impl.vo.response.UserPrivacyListResponse;
import org.routemaster.api.auth.endpoint.user.info.privacy.impl.vo.response.UserPrivacySaveResponse;

public interface UserPrivacyEndpointService {


    UserPrivacyListResponse details(UserJwtPayload payload);
    UserPrivacySaveResponse save(UserJwtPayload payload, UserPrivacySaveRequest request);
    UserPrivacySaveResponse saveAll(UserJwtPayload payload, UserPrivacySaveAllRequest request);
    UserPrivacyDeleteResponse delete(String id, UserJwtPayload payload);
    UserPrivacyDeleteResponse deleteAll(List<String> ids, UserJwtPayload payload);
}
