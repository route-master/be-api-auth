package org.routemaster.api.auth.domain.user.domain.basic.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.Set;

@Schema(
        name = "basicUserReady",
        description = "회원가입 완료 전 이메일 검증 대기를 위한 단계에 위치한 단일 사용자 정보"
)
@Document(collection = "basic_user_ready")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class BasicUserReady implements Serializable {

    public static final String TYPE = BasicUserReady.class.getName();

    private static final long serialVersionUID = (long) TYPE.hashCode();

    public static final int VALIDATION_CODE_SIZE = 16;

    @Schema(
            description = "객체별 고유 id"
    )
    @Id
    @JsonIgnore
    private String id;

    @Schema(
            description = "이메일"
    )
    @Indexed(unique = true)
    @Field(name = "username")
    private String username;
    @Schema(
            description = "암호화 과정을 거친 비밀번호"
    )
    @Field(name = "password")
    private String password;
    @Schema(
            description = "암호화 과정을 거친 비밀번호"
    )
    @Field(name = "authorities")
    private Set<String> authorities;
    @Schema(
            description = "암호화 과정을 거친 비밀번호"
    )
    @Field(name = "verification_code")
    private String verificationCode;
    @Schema(
            description = "생성 시간 (밀리초 단위의 표준시간(UTC)간의 차이)"
    )
    @Field(name = "created_at")
    private Long createdAt;
    @Schema(
            description = "만료 시간 (밀리초 단위의 표준시간(UTC)간의 차이)"
    )
    @Field(name = "expired_at")
    private Long expiredAt;
}