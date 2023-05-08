package org.routemaster.api.auth.domain.user.domain.basic.util.mapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.routemaster.api.auth.domain.user.domain.basic.data.BasicUserReady;
import org.routemaster.api.auth.domain.user.domain.basic.data.vo.create.BasicUserCreateVO;
import org.routemaster.api.auth.domain.user.domain.basic.data.vo.response.BasicUserReadySecuredResponseVO;
import org.routemaster.api.auth.domain.user.util.constant.UserTimeConstant;
import org.routemaster.api.auth.global.util.StringGenerationUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class BasicUserReadyMapper {

    private final StringGenerationUtils stringGenerationUtils;
    private final PasswordEncoder passwordEncoder;

    public BasicUserReady fromCreateVO(BasicUserCreateVO createVO) {
        return BasicUserReady.builder()
                .username(createVO.getUsername())
                .password(passwordEncoder.encode(createVO.getPassword()))
                .authorities(createVO.getAuthorities())
                .verificationCode(stringGenerationUtils.genereateRandomString(BasicUserReady.VALIDATION_CODE_SIZE))
                .createdAt(System.currentTimeMillis())
                .expiredAt(System.currentTimeMillis() + UserTimeConstant.ACCOUNT_READY_EXPIRATION_DURATION)
                .build();
    }

    public BasicUserReady fromCreateVO(BasicUserReady basicUserReady, BasicUserCreateVO createVO) {
        return BasicUserReady.builder()
                .id(basicUserReady.getId())
                .username(createVO.getUsername())
                .password(passwordEncoder.encode(createVO.getPassword()))
                .authorities(createVO.getAuthorities())
                .verificationCode(stringGenerationUtils.genereateRandomString(BasicUserReady.VALIDATION_CODE_SIZE))
                .createdAt(basicUserReady.getCreatedAt())
                .expiredAt(System.currentTimeMillis() + UserTimeConstant.ACCOUNT_READY_EXPIRATION_DURATION)
                .build();
    }

    public BasicUserReadySecuredResponseVO toBasicUserReadySecuredResponseVO(BasicUserReady basicUserReady) {
        return BasicUserReadySecuredResponseVO.builder()
                .data(basicUserReady)
                .meta(createRegisterBasicUserMetadata(basicUserReady))
                .build();
    }

    private Map<String, Object> createRegisterBasicUserMetadata(BasicUserReady basicUserReady) {
        Map<String, Object> meta = new HashMap<>();
        return meta;
    }
}
