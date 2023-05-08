package org.routemaster.api.auth.domain.user.domain.basic.util.mapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.routemaster.api.auth.domain.user.domain.basic.data.BasicUser;
import org.routemaster.api.auth.domain.user.domain.basic.data.vo.details.BasicUserDetailsSecuredResponseVO;
import org.springframework.context.annotation.ComponentScan;

@Slf4j
@ComponentScan
@RequiredArgsConstructor
public class BasicUserMapper {

    public BasicUserDetailsSecuredResponseVO toDetailsSecuredResponseVO(BasicUser basicUser) {
        return BasicUserDetailsSecuredResponseVO.builder()
                .username(basicUser.getUsername())
                .authorities(basicUser.getStringAuthorities())
                .createdAt(basicUser.getCreatedAt())
                .updatedAt(basicUser.getUpdatedAt())
                .passwordUpdatedAt(basicUser.getPasswordUpdatedAt())
                .lockExpiredAt(basicUser.getLockExpiredAt())
                .build();
    }
}
