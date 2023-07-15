package org.routemaster.api.auth.endpoint.user.info.profile.impl.vo.request;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;
import org.routemaster.api.auth.domain.user.info.profile.impl.util.constant.ProfileAccessType;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class UserProfileSaveRequest {

    @NotNull
    @Length(min = 2, max = 32)
    private String nickname;

    private LocalDateTime birthDate;

    @URL
    private String profileImageUrl;

    @NotNull
    private ProfileAccessType accessType;
}
