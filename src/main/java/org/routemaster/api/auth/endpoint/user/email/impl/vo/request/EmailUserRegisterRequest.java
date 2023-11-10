package org.routemaster.api.auth.endpoint.user.email.impl.vo.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.List;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.routemaster.api.auth.endpoint.user.info.privacy.impl.vo.request.UserPrivacySaveAllRequest;
import org.routemaster.api.auth.endpoint.user.info.privacy.impl.vo.request.UserPrivacySaveRequest;
import org.routemaster.api.auth.endpoint.user.info.profile.impl.vo.request.UserProfileTotalSaveRequest;
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

    @NotNull
    private UserPrivacySaveAllRequest privacy;
    @NotNull
    private UserProfileTotalSaveRequest profile;
}
