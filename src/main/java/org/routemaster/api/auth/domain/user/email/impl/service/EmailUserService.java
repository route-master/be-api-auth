package org.routemaster.api.auth.domain.user.email.impl.service;

import org.routemaster.api.auth.domain.user.email.impl.data.EmailUser;

public interface EmailUserService {

    EmailUser register(String username, String password);

    EmailUser verifyRegister(String username, String verificationCode);
    EmailUser updatePassword(String username, String password);
    EmailUser updateRefreshToken(String username, String refreshToken);
    void delete(String username);
    EmailUser detailsByUsername(String username);
    EmailUser details(String id);
}
