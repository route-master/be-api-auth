package org.routemaster.api.auth.domain.user.util.constant;

import org.routemaster.api.auth.global.constant.MilliSecondConstant;

public class UserTimeConstant {
    public static final long ACCOUNT_EXPIRATION_DURATION = 300 * MilliSecondConstant.DAY;
    public static final long CREDENTIALS_EXPIRATION_DURATION = 90 * MilliSecondConstant.DAY;
    public static final long ACCOUNT_READY_EXPIRATION_DURATION = 7 * MilliSecondConstant.DAY;
}
