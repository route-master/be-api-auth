package org.routemaster.api.auth.infra.oauth2.vo;

import java.util.Map;

public interface Oauth2UserInfo {

    Map<String, Object> getAttributes();
    Long getId();
    String getProvider();
}
