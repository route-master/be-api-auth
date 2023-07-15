package org.routemaster.api.auth.domain.user.email.impl.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import java.time.LocalDateTime;
import lombok.*;
import org.routemaster.api.auth.domain.user.impl.data.DefaultUserDetails;
import org.routemaster.api.auth.domain.user.impl.util.constant.UserTime;
import org.routemaster.api.auth.domain.user.impl.util.constant.UserType;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Document(collection = "basic_user")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
@ToString
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class EmailUser implements DefaultUserDetails, Serializable {

    public static final UserType TYPE = UserType.EMAIL_USER;
    private static final long serialVersionUID = (long) TYPE.hashCode();
    @Id
    private String id;
    @Indexed(unique = true)
    @Field(name = "username")
    private String username;
    @Field(name = "password")
    private String password;
    @Field(name = "authorities")
    private Set<String> authorities;
    @Field(name = "created_at")
    private LocalDateTime createdAt;
    @Field(name = "updated_at")
    private LocalDateTime updatedAt;
    @Field(name = "password_updated_at")
    private LocalDateTime passwordUpdatedAt;
    @Field(name = "last_active_time")
    private LocalDateTime lastActiveTime;
    @Field(name = "lock_expired_at")
    private LocalDateTime lockExpiredAt;
    @Field(name = "refresh_token")
    private String refreshToken;

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        LocalDateTime.now();
        return authorities.stream()
                .map(authority -> new SimpleGrantedAuthority(authority))
                .collect(Collectors.toSet());
    }

    @JsonProperty("authorities")
    public Set<String> getStringAuthorities() {
        return authorities;
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
        LocalDateTime expiredAt = passwordUpdatedAt.plusDays(UserTime.CREDENTIALS_EXPIRATION_DAYS);
        LocalDateTime now = LocalDateTime.now();
        return  now.isAfter(expiredAt);
    }

    @Override
    public boolean isEnabled() {
        return isAccountNonExpired()
                && isAccountNonLocked()
                && isCredentialsNonExpired();
    }

    @JsonIgnore
    @Override
    public UserType getType() {
        return TYPE;
    }

    public void setPassword(String password) {
        this.password = password;
        this.passwordUpdatedAt = LocalDateTime.now();
        setUpdatedAt();
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
