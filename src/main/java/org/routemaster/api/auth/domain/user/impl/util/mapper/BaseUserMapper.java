package org.routemaster.api.auth.domain.user.impl.util.mapper;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.routemaster.api.auth.domain.user.impl.data.BaseUser;
import org.routemaster.api.auth.domain.user.impl.exception.BaseUserErrorDescription;
import org.routemaster.api.auth.domain.user.impl.exception.UserErrorCode;
import org.routemaster.api.auth.domain.user.impl.util.constant.UserType;
import org.routemaster.sdk.exception.data.roe.ROEFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class BaseUserMapper {

    private final ROEFactory roeFactory;
    public BaseUser register(UserType type, String id) {
        switch (type) {
            case EMAIL_USER: return emailUserRegister(id);
            case SOCIAL_USER: return socialUserRegister(id);
            default: throw roeFactory.get(
                UserErrorCode.ROE_100,
                BaseUserErrorDescription.INVALID_USER_TYPE,
                HttpStatus.BAD_REQUEST
            );
        }
    }

    private BaseUser emailUserRegister(String id) {
        return BaseUser.builder()
            .emailUsers(new HashSet<>(Arrays.asList(id)))
            .socialUsers(new HashSet<>())
            .build();
    }

    private BaseUser socialUserRegister(String id) {
        return BaseUser.builder()
            .emailUsers(new HashSet<>())
            .socialUsers(new HashSet<>(Arrays.asList(id)))
            .build();
    }

    public BaseUser merge(BaseUser standard, BaseUser other) {
        Set<String> emailUsers = new HashSet<>(standard.getEmailUsers());
        Set<String> socialUsers = new HashSet<>(standard.getSocialUsers());

        emailUsers.addAll(other.getEmailUsers());
        socialUsers.addAll(other.getSocialUsers());

        return BaseUser.builder()
            .id(standard.getId())
            .emailUsers(emailUsers)
            .socialUsers(socialUsers)
            .build();
    }
}
