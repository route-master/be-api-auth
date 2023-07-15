package org.routemaster.api.auth.domain.user.info.profile.impl.data;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.routemaster.api.auth.domain.user.info.profile.impl.util.constant.ProfileAccessType;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collation = "user_profile")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UserProfile {

    @Id
    private String id;
    @Indexed(unique = true)
    @Field(name = "base_user_id")
    private String baseUserId;
    @Indexed(unique = true)
    @Field(name = "nickname")
    private String nickname;
    @Field(name = "birth_date")
    private LocalDateTime birthDate;
    @Field(name = "profile_image_url")
    private String profileImageUrl;
    @Field(name = "created_at")
    private LocalDateTime createdAt;
    @Field(name = "updated_at")
    private LocalDateTime updatedAt;
    @Field(name = "access_type")
    private ProfileAccessType accessType;
}
