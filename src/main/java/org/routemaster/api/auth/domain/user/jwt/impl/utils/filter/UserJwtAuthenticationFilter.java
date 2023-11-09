package org.routemaster.api.auth.domain.user.jwt.impl.utils.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.routemaster.api.auth.domain.user.email.impl.service.EmailUserService;
import org.routemaster.api.auth.domain.user.impl.data.DefaultUserDetails;
import org.routemaster.api.auth.domain.user.impl.exception.UserErrorCode;
import org.routemaster.api.auth.domain.user.impl.service.BaseUserService;
import org.routemaster.api.auth.domain.user.jwt.impl.data.UserJwtPayload;
import org.routemaster.api.auth.domain.user.jwt.impl.exception.UserJwtErrorDescription;
import org.routemaster.api.auth.domain.user.jwt.impl.service.UserJwtService;
import org.routemaster.api.auth.domain.user.jwt.impl.utils.constant.JwtType;
import org.routemaster.api.auth.domain.user.social.impl.service.SocialUserService;
import org.routemaster.sdk.exception.data.roe.ROEFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserJwtAuthenticationFilter extends OncePerRequestFilter {

    public static final String USER_PAYLOAD = "USER_PAYLOAD";
    public static final String USER_DETAILS = "USER_DETAILS";

    protected AuthenticationDetailsSource<HttpServletRequest, ?> authenticationDetailsSource = new WebAuthenticationDetailsSource();

    private final UserJwtService userJwtService;
    private final BaseUserService baseUserService;
    private final EmailUserService emailUserService;
    private final SocialUserService socialUserService;

    private final ROEFactory roeFactory;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
        FilterChain filterChain) throws ServletException, IOException {
        String token = parseBearerToken(request);
        if (token == null) {
            filterChain.doFilter(request, response);
            return;
        }

        if (!userJwtService.validateToken(token)) {
            throw roeFactory.get(
                UserErrorCode.ROE_102,
                UserJwtErrorDescription.INVALID_TOKEN,
                HttpStatus.UNAUTHORIZED
            );
        }

        UserJwtPayload payload = userJwtService.getPayload(token);

        DefaultUserDetails userDetails;
        try {
            userDetails = switch (payload.getUserType()) {
                case EMAIL_USER -> emailUserService.details(payload.getTypeUserId());
                case SOCIAL_USER -> socialUserService.details(payload.getTypeUserId());
                default -> throw roeFactory.get(
                    UserErrorCode.ROE_102,
                    UserJwtErrorDescription.INVALID_TOKEN,
                    HttpStatus.UNAUTHORIZED
                );
            };
        } catch (Exception e) {
            throw roeFactory.get(
                UserErrorCode.ROE_102,
                UserJwtErrorDescription.INVALID_TOKEN,
                HttpStatus.UNAUTHORIZED
            );
        }

        if (payload.getJwtType() == JwtType.REFRESH_TOKEN) {
            if (!userDetails.getRefreshToken().equals(token)) {
                throw roeFactory.get(
                    UserErrorCode.ROE_102,
                    UserJwtErrorDescription.INVALID_TOKEN,
                    HttpStatus.UNAUTHORIZED
                );
            }
        }

        request.setAttribute(USER_PAYLOAD, payload);
        request.setAttribute(USER_DETAILS, userDetails);

        Authentication authentication = attempAuthentication(request, response, userDetails);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        filterChain.doFilter(request, response);
    }

    private String parseBearerToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    private Authentication attempAuthentication(HttpServletRequest request, HttpServletResponse response, DefaultUserDetails userDetails)
        throws AuthenticationException {
        String username = userDetails.getUsername();
        String password = userDetails.getPassword();
        UsernamePasswordAuthenticationToken authRequest = UsernamePasswordAuthenticationToken.authenticated(username, password, userDetails.getAuthorities());
        setDetails(request, authRequest);
        return authRequest;
    }

    protected void setDetails(HttpServletRequest request, UsernamePasswordAuthenticationToken authRequest) {
        authRequest.setDetails(this.authenticationDetailsSource.buildDetails(request));
    }
}
