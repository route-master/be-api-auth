package org.routemaster.api.auth.domain.user.domain.basic.util.email;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.routemaster.api.auth.domain.user.domain.basic.data.BasicUserReady;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class BasicUserValidationMailUtils {

    private static final String SUBJECT  = "[Route Master] 회원 검증 코드를 확인해주세요.";
    private static final String TEXT = "검증 코드: {}";

    public String createSubject(BasicUserReady basicUserReady) {
        return SUBJECT;
    }

    public String createText(BasicUserReady basicUserReady) {
        return TEXT.replace("{}", basicUserReady.getValidationCode());
    }
}
