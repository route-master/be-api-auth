package org.routemaster.api.auth.endpoint.user.email.impl.util.mapper;

import java.util.Arrays;
import java.util.HashSet;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.routemaster.api.auth.domain.user.email.impl.data.EmailUser;
import org.routemaster.api.auth.domain.user.impl.data.BaseUser;
import org.routemaster.api.auth.domain.user.social.impl.data.SocialUser;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class EndpointBaseUserMapper {

    public BaseUser register(EmailUser emailUser) {
        return BaseUser.builder()
            .emailUsers(new HashSet<>(Arrays.asList(emailUser.getId())))
            .socialUsers(new HashSet<>())
            .build();
    }

    public BaseUser register(SocialUser socialUser) {
        return BaseUser.builder()
            .emailUsers(new HashSet<>())
            .socialUsers(new HashSet<>(Arrays.asList(socialUser.getId())))
            .build();
    }
}
