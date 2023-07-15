package org.routemaster.api.auth.global.domain.jwt.impl.service.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.routemaster.api.auth.global.domain.jwt.impl.service.JwtService;
import org.routemaster.api.auth.global.domain.jwt.impl.vo.JwtUnit;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class DefaultJwtService implements JwtService {

    @Override
    public JwtUnit createToken(String payload, Long expiresIn, String secret) {
        Claims claims = Jwts.claims().setSubject(payload);

        Date now = new Date();
        Date expiresDate = new Date(now.getTime() + expiresIn * 1000);

        String token = Jwts.builder()
            .setClaims(claims)
            .setIssuedAt(now)
            .setExpiration(expiresDate)
            .signWith(SignatureAlgorithm.HS512, secret)
            .compact();

        return JwtUnit.builder()
            .token(token)
            .expiresIn(expiresIn)
            .build();
    }

    @Override
    public String getPayload(String token, String secret) {
        return Jwts.parser()
            .setSigningKey(secret)
            .parseClaimsJws(token)
            .getBody()
            .getSubject();
    }

    @Override
    public Boolean validateToken(String token, String secret) {
        Jws<Claims> claimsJws = Jwts.parser()
            .setSigningKey(secret)
            .parseClaimsJws(token);

        return !claimsJws.getBody()
            .getExpiration()
            .before(new Date());
    }
}
