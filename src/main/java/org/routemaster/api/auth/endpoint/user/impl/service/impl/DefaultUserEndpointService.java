package org.routemaster.api.auth.endpoint.user.impl.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.routemaster.api.auth.domain.user.email.impl.service.EmailUserService;
import org.routemaster.api.auth.domain.user.impl.data.BaseUser;
import org.routemaster.api.auth.domain.user.impl.exception.UserErrorCode;
import org.routemaster.api.auth.domain.user.impl.service.BaseUserService;
import org.routemaster.api.auth.domain.user.info.privacy.impl.service.UserPrivacyService;
import org.routemaster.api.auth.domain.user.info.profile.impl.service.UserProfileAccessService;
import org.routemaster.api.auth.domain.user.info.profile.impl.service.UserProfileService;
import org.routemaster.api.auth.domain.user.info.setting.impl.service.UserSettingService;
import org.routemaster.api.auth.domain.user.jwt.impl.data.UserJwtPayload;
import org.routemaster.api.auth.domain.user.jwt.impl.exception.UserJwtErrorDescription;
import org.routemaster.api.auth.domain.user.social.impl.service.SocialUserService;
import org.routemaster.api.auth.endpoint.user.impl.service.UserEndpointService;
import org.routemaster.sdk.exception.data.roe.ROEFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class DefaultUserEndpointService implements UserEndpointService {

    private final UserPrivacyService userPrivacyService;
    private final UserProfileService userProfileService;
    private final UserProfileAccessService userProfileAccessService;
    private final UserSettingService userSettingService;
    private final BaseUserService baseUserService;
    private final EmailUserService emailUserService;
    private final SocialUserService socialUserService;
    private final ROEFactory roeFactory;

    @Override
    public void logout(UserJwtPayload payload) {
        switch (payload.getUserType()) {
            case EMAIL_USER:
                emailUserService.updateRefreshToken(payload.getTypeUserId(), null);
                break;
            case SOCIAL_USER:
                socialUserService.updateRefreshToken(payload.getTypeUserId(), null);
                break;
            default: throw roeFactory.get(
                UserErrorCode.ROE_102,
                UserJwtErrorDescription.INVALID_TOKEN,
                HttpStatus.UNAUTHORIZED
            );
        }
    }

    @Override
    @Transactional
    public void delete(UserJwtPayload payload) {
        BaseUser baseUser = baseUserService.details(payload.getBaseUserId());
        switch (payload.getUserType()) {
            case EMAIL_USER:
                emailUserService.delete(payload.getTypeUserId());
                baseUser.getEmailUsers().remove(payload.getTypeUserId());
                break;
            case SOCIAL_USER:
                socialUserService.delete(payload.getTypeUserId());
                baseUser.getSocialUsers().remove(payload.getTypeUserId());
                break;
            default: throw roeFactory.get(
                UserErrorCode.ROE_102,
                UserJwtErrorDescription.INVALID_TOKEN,
                HttpStatus.UNAUTHORIZED
            );
        }

        if (baseUser.getEmailUsers().isEmpty() && baseUser.getSocialUsers().isEmpty()) {
            baseUserService.delete(baseUser.getId());
            userProfileService.deleteByBaseUserId(baseUser.getId());
            userProfileAccessService.deleteByBaseUserId(baseUser.getId());
            userSettingService.deleteByBaseUserId(baseUser.getId());
            userPrivacyService.deleteAllByBaseUserId(baseUser.getId());
        }
        else {
            baseUserService.save(baseUser);
        }
    }
}
