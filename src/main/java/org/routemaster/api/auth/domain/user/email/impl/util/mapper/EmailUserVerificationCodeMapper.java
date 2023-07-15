package org.routemaster.api.auth.domain.user.email.impl.util.mapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.routemaster.api.auth.domain.user.email.impl.data.EmailUserReady;
import org.routemaster.api.auth.domain.user.email.impl.util.email.EmailUserVerificationUtils;
import org.routemaster.api.auth.global.util.StringGenerationUtils;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class EmailUserVerificationCodeMapper {

    public static final int USER_VERIFICATION_CODE_SIZE = 6;
    public static final int DEFAULT_VERIFICATION_CODE_SIZE = 64;

    private final StringGenerationUtils stringGenerationUtils;
    private final EmailUserVerificationUtils basicUserValidationUtils;

    public String generate(EmailUserReady userReady) {
        if (basicUserValidationUtils.hasSingleRoleUser(userReady)) {
            return stringGenerationUtils.genereateRandomString(USER_VERIFICATION_CODE_SIZE);
        }
        return stringGenerationUtils.genereateRandomString(DEFAULT_VERIFICATION_CODE_SIZE);
    }
}
