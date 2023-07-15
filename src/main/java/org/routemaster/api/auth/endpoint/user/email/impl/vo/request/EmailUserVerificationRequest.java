package org.routemaster.api.auth.endpoint.user.email.impl.vo.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
public class EmailUserVerificationRequest {

    @Email
    private String username;

    @NotNull
    @Length(min = 1, max = 64)
    private String verificationCode;
}
