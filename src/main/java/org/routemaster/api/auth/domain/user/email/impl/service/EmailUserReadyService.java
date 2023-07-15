package org.routemaster.api.auth.domain.user.email.impl.service;

import org.routemaster.api.auth.domain.user.email.impl.data.EmailUser;
import org.routemaster.api.auth.domain.user.email.impl.data.EmailUserReady;
import org.springframework.transaction.annotation.Transactional;

public interface EmailUserReadyService {

    EmailUserReady register(EmailUserReady user);
    EmailUserReady details(String username);
    void delete(String username);
}
