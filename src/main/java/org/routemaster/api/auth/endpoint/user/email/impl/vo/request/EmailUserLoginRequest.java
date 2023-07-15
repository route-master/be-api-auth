package org.routemaster.api.auth.endpoint.user.email.impl.vo.request;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.routemaster.api.auth.global.util.validation.constraints.Password;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class EmailUserLoginRequest {

    @Email
    private String username;

    @Password
    private String password;
}
