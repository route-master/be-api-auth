package org.routemaster.api.auth.domain.user.domain.basic.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.Set;

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

    @Id
    @Field("_id")
    @JsonIgnore
    private String id;
    @Indexed(unique = true)
    @Field(name = "username")
    private String username;
    @Field(name = "password")
    private String password;
    @Field(name = "authorities")
    private Set<String> authorities;
    @Field(name = "validation_code")
    private String validationCode;
    @Field(name = "created_at")
    private Long createdAt;
    @Field(name = "expired_at")
    private Long expiredAt;
}