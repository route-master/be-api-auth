package org.routemaster.api.auth.endpoint.user.social.vo.request;

import java.util.List;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.routemaster.api.auth.endpoint.user.info.privacy.impl.vo.request.UserPrivacySaveAllRequest;
import org.routemaster.api.auth.endpoint.user.info.privacy.impl.vo.request.UserPrivacySaveRequest;
import org.routemaster.api.auth.endpoint.user.info.profile.impl.vo.request.UserProfileTotalSaveRequest;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class SocialUserRegisterRequest {

    private String accessToken;
    private UserPrivacySaveAllRequest privacy;
    private UserProfileTotalSaveRequest profile;
}
