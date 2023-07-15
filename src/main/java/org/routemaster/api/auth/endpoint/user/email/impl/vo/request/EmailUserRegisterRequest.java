package org.routemaster.api.auth.endpoint.user.email.impl.vo.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.routemaster.api.auth.global.util.validation.constraints.Password;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class EmailUserRegisterRequest {

    @Email
    private String username;
    @Password
    private String password;

    // "ROLE_USER"
    @NotNull
    @Size(min = 1, max = 5)
    private Set<@Length(min = 1, max = 32) String> authorities;
}
