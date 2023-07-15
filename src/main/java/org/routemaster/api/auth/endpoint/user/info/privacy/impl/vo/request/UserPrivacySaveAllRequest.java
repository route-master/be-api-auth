package org.routemaster.api.auth.endpoint.user.info.privacy.impl.vo.request;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class UserPrivacySaveAllRequest {

    List<UserPrivacySaveRequest> privacies;
}
