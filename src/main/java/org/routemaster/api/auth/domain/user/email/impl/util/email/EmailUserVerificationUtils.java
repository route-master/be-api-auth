package org.routemaster.api.auth.domain.user.email.impl.util.email;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.routemaster.api.auth.domain.user.email.impl.data.EmailUserReady;
import org.routemaster.api.auth.domain.user.impl.util.constant.UserRole;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Set;

@Slf4j
@Component
@RequiredArgsConstructor
public class EmailUserVerificationUtils {

    private final EmailUserVerificationMailUtils mailUtils;

    @Async
    public void doBeforeVerification(EmailUserReady basicUserReady) {
        if (hasSingleRoleUser(basicUserReady)) {
            mailUtils.sendVerificationCode(basicUserReady);
        }
    }

    public Boolean hasSingleRoleUser(EmailUserReady basicUserReady) {
        Set<String> authorities = basicUserReady.getAuthorities();
        return authorities.size() == 1 && authorities.contains(UserRole.ROLE_USER);
    }
}
