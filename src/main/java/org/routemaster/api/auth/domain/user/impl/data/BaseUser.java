package org.routemaster.api.auth.domain.user.impl.data;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Set;

@Document(collection = "base_user")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
public class BaseUser {

    public static final String TYPE = BaseUser.class.getName();

    private static final long serialVersionUID = (long) TYPE.hashCode();

    @Id
    private String id;

    @Field(name = "email_users")
    private Set<String> emailUsers;

    @Field(name = "social_users")
    private Set<String> socialUsers;
}
