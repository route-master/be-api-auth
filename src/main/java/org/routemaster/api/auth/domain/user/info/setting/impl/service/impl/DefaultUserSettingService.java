package org.routemaster.api.auth.domain.user.info.setting.impl.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.routemaster.api.auth.domain.user.info.impl.exception.InfoErrorCode;
import org.routemaster.api.auth.domain.user.info.setting.impl.data.UserSetting;
import org.routemaster.api.auth.domain.user.info.setting.impl.exception.UserSettingErrorDescription;
import org.routemaster.api.auth.domain.user.info.setting.impl.persistence.UserSettingRepository;
import org.routemaster.api.auth.domain.user.info.setting.impl.service.UserSettingService;
import org.routemaster.sdk.exception.data.roe.ROEFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class DefaultUserSettingService implements UserSettingService {

    private final UserSettingRepository userSettingRepository;

    private final ROEFactory roeFactory;

    @Override
    public UserSetting details(String id) {
        return userSettingRepository.findById(id)
            .orElseThrow(() -> roeFactory.get(
                InfoErrorCode.ROE_111,
                UserSettingErrorDescription.USER_SETTING_NOT_FOUND,
                HttpStatus.NOT_FOUND
            ));
    }

    @Override
    public UserSetting detailsByBaseUserId(String baseUserId) {
        return userSettingRepository.findByBaseUserId(baseUserId)
            .orElseThrow(() -> roeFactory.get(
                InfoErrorCode.ROE_111,
                UserSettingErrorDescription.USER_SETTING_NOT_FOUND,
                HttpStatus.NOT_FOUND
            ));
    }

    @Override
    public UserSetting save(UserSetting userSetting) {
        return userSettingRepository.save(userSetting);
    }

    @Override
    public void delete(String id) {
        if (!userSettingRepository.existsById(id)) {
            throw roeFactory.get(
                InfoErrorCode.ROE_111,
                UserSettingErrorDescription.USER_SETTING_NOT_FOUND,
                HttpStatus.NOT_FOUND
            );
        }
        userSettingRepository.deleteById(id);
    }

    @Override
    public void deleteByBaseUserId(String baseUserId) {
        userSettingRepository.deleteByBaseUserId(baseUserId);
    }
}
