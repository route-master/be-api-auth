package org.routemaster.api.auth.domain.user.domain.basic.service;

import org.routemaster.api.auth.domain.user.domain.basic.data.BasicUser;
import org.routemaster.api.auth.domain.user.domain.basic.data.BasicUserReady;
import org.routemaster.api.auth.domain.user.domain.basic.data.vo.search.BasicUserReadySearchVO;
import org.routemaster.api.auth.domain.user.domain.basic.data.vo.search.BasicUserSearchVO;
import org.springframework.data.domain.Page;

public interface BasicUserAdminQueryService {

    Page<BasicUser> basicUserPage(BasicUserSearchVO searchVO);
    Page<BasicUserReady> basicUserReadyPage(BasicUserReadySearchVO searchVO);
}
