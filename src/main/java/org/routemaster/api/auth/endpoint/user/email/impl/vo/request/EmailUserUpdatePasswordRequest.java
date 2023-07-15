package org.routemaster.api.auth.endpoint.user.email.impl.vo.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.routemaster.api.auth.global.util.validation.constraints.Password;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class EmailUserUpdatePasswordRequest {

    @Password
    private String password;
}
