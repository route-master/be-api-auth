package org.routemaster.api.auth.domain.user.social.impl.data;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import java.time.LocalDateTime;
import lombok.*;
import org.routemaster.api.auth.domain.user.impl.data.DefaultUserDetails;
import org.routemaster.api.auth.domain.user.impl.util.constant.UserTime;
import org.routemaster.api.auth.domain.user.impl.util.constant.UserType;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Document(collection = "social_user")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
public class SocialUser implements DefaultUserDetails, Serializable {

    public static final UserType TYPE = UserType.SOCIAL_USER;
    private static final long serialVersionUID = (long) TYPE.hashCode();

    @Id
    private String id;
    @Field(name = "provider")
    private String provider;
    @Field(name = "social_id")
    private Long socialId;
    @Field(name = "authorities")
    private Set<String> authorities;
    @Field(name = "created_at")
    private LocalDateTime createdAt;
    @Field(name = "updated_at")
    private LocalDateTime updatedAt;
    @Field(name = "last_active_time")
    private LocalDateTime lastActiveTime;
    @Field(name = "lock_expired_at")
    private LocalDateTime lockExpiredAt;
    @Field(name = "refresh_token")
    private String refreshToken;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities.stream()
                .map(authority -> new SimpleGrantedAuthority(authority))
                .collect(Collectors.toSet());
    }

    @Override
    public String getUsername() {
        return String.format("username|%s|%d|", provider, socialId);
    }

    @Override
    public String getPassword() {
        return String.format("password|%s|%d|", provider, socialId);
    }

    @Override
    public boolean isAccountNonExpired() {
        LocalDateTime expiredAt = lastActiveTime.plusDays(UserTime.ACCOUNT_EXPIRATION_DAYS);
        LocalDateTime now = LocalDateTime.now();
        return  now.isAfter(expiredAt);
    }

    @Override
    public boolean isAccountNonLocked() {
        LocalDateTime now = LocalDateTime.now();
        return lockExpiredAt == null
            || now.isBefore(lockExpiredAt);
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isAccountNonExpired()
                && isAccountNonLocked()
                && isCredentialsNonExpired();
    }

    @Override
    public UserType getType() {
        return TYPE;
    }

    @Override
    public Set<String> getStringAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<String> authorities) {
        this.authorities = authorities;
        setUpdatedAt();
    }

    public void addAuthority(String authority) {
        this.authorities.add(authority);
        setUpdatedAt();
    }

    public void removeAuthority(String authority) {
        this.authorities.remove(authority);
        setUpdatedAt();
    }

    private void setUpdatedAt() {
        this.updatedAt = LocalDateTime.now();
    }

    public void setLastActiveTime() {
        this.lastActiveTime = LocalDateTime.now();
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
        setUpdatedAt();
    }
}
