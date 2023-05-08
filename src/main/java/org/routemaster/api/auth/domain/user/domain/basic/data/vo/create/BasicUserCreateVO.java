package org.routemaster.api.auth.domain.user.domain.basic.data.vo.create;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.routemaster.api.auth.global.util.validation.constraints.Password;

import java.util.Set;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
@ToString
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class BasicUserCreateVO {

    @NotNull
    @Email
    private String username;
    @NotNull
    @Password
    private String password;
    @NotNull
    @Size(min = 1)
    private Set<@Size(min = 1, max = 64) String> authorities;
}