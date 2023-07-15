package org.routemaster.api.auth.global.domain.jwt.impl.service;

import org.routemaster.api.auth.global.domain.jwt.impl.vo.JwtUnit;

public interface JwtService {

    JwtUnit createToken(String payload, Long expiresIn, String secret);
    String getPayload(String token, String secret);
    Boolean validateToken(String token, String secret);
}
