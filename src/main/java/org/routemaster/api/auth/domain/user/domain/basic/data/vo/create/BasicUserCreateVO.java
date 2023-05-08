package org.routemaster.api.auth.domain.user.domain.basic.data.vo.create;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.routemaster.api.auth.global.util.validation.constraints.Password;

import java.util.Set;

@Schema(
        name = "basicUserCreate",
        description = "사용자 회원가입 요청에 필요한 정보"
)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
@ToString
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class BasicUserCreateVO {

    @Schema(
            description = "이메일",
            example = "username@gmail.com"
    )
    @NotNull
    @Email
    private String username;
    @Schema(
            description = "비밀번호 (알파벳, 숫자, 특수문자를 1개 이상 사용한 8~16글자 텍스트)"
    )
    @NotNull
    @Password
    private String password;
    @Schema(
            description = "사용자 권한",
            example = "[ \"ROLE_USER\" ]"
    )
    @NotNull
    @Size(min = 1)
    private Set<@Size(min = 1, max = 64) String> authorities;
}