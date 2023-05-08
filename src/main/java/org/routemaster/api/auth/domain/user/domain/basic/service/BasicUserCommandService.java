package org.routemaster.api.auth.domain.user.domain.basic.service;

import org.routemaster.api.auth.domain.user.domain.basic.data.BasicUser;
import org.routemaster.api.auth.domain.user.domain.basic.data.vo.create.BasicUserCreateVO;
import org.routemaster.api.auth.domain.user.domain.basic.data.vo.details.BasicUserDetailsSecuredResponseVO;
import org.routemaster.api.auth.domain.user.domain.basic.data.vo.response.BasicUserReadySecuredResponseVO;
import reactor.core.publisher.Mono;

public interface BasicUserCommandService {

    Mono<BasicUserReadySecuredResponseVO> registerBasicUser(BasicUserCreateVO createVO);
    BasicUserDetailsSecuredResponseVO validateRegisterBasicUser(Long id, String validationCode);
    BasicUser updatePassword(Long id, String password);
    BasicUser updatePassword(String username, String password);
    BasicUser deleteBasicUser(Long id);
    BasicUser deleteBasicUser(String username);
}
