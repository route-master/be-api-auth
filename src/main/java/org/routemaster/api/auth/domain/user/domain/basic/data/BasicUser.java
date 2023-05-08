package org.routemaster.api.auth.domain.user.domain.basic.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;
import org.routemaster.api.auth.domain.user.data.DefaultUserDetails;
import org.routemaster.api.auth.domain.user.util.constant.UserTimeConstant;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.io.Serializable;
import java.time.Duration;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Document(collection = "basic_user")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class BasicUser implements DefaultUserDetails, Serializable {
    public static final String TYPE = BasicUser.class.getName();

    private static final long serialVersionUID = (long) TYPE.hashCode();
    @Id
    @Field("_id")
    @JsonIgnore
    private String id;
    @Indexed(unique = true)
    @Field(name = "username")
    private String username;
    @Field(name = "password")
    private String password;
    @Field(name = "authorities")
    private Set<String> authorities;
    @Field(name = "created_at")
    private Long createdAt;
    @Field(name = "updated_at")
    private Long updatedAt;
    @Field(name = "deleted_at")
    private Long deletedAt;
    @Field(name = "password_updated_at")
    private Long passwordUpdatedAt;
    @Field(name = "last_active_time")
    private Long lastActiveTime;
    @Field(name = "lock_expired_at")
    private Long lockExpiredAt;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities.stream()
                .map(authority -> new SimpleGrantedAuthority(authority))
                .collect(Collectors.toSet());
    }

    public Set<String> getStringAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        long duration = System.currentTimeMillis() - lastActiveTime;
        return !Duration.ofMillis(UserTimeConstant.ACCOUNT_EXPIRATION_DURATION - duration)
                .isNegative();
    }

    @Override
    public boolean isAccountNonLocked() {
        return lockExpiredAt == null
                || lockExpiredAt < System.currentTimeMillis();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        long duration = System.currentTimeMillis() - passwordUpdatedAt;
        return !Duration.ofMillis(UserTimeConstant.CREDENTIALS_EXPIRATION_DURATION - duration)
                .isNegative();
    }

    @Override
    public boolean isEnabled() {
        return isAccountNonExpired()
                && isAccountNonLocked()
                && isCredentialsNonExpired();
    }

    @Override
    public String getType() {
        return TYPE;
    }

    public void setPassword(String password) {
        this.password = password;
        this.passwordUpdatedAt = System.currentTimeMillis();
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

    public void setDeletedAt() {
        this.deletedAt = System.currentTimeMillis();
    }

    public void setLastActiveTime() {
        this.lastActiveTime = System.currentTimeMillis();
    }

    private void setUpdatedAt() {
        this.updatedAt = System.currentTimeMillis();
    }
}
