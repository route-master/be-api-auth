package org.routemaster.api.auth.global.util;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class StringGenerationUtils {

    public static final String UTF_8 = "UTF-8";

    public String genereateRandomString(int size) {
        return RandomStringUtils.randomAlphabetic(size);
    }
}
