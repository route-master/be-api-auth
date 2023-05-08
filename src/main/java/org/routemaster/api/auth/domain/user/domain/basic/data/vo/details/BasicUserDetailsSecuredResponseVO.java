package org.routemaster.api.auth.domain.user.domain.basic.data.vo.details;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import java.util.Set;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
@ToString
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class BasicUserDetailsSecuredResponseVO {

    private String username;
    private Set<String> authorities;
    private Long createdAt;
    private Long updatedAt;
    private Long passwordUpdatedAt;
    private Long lockExpiredAt;
}
