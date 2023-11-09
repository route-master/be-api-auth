package org.routemaster.api.auth.domain.user.impl.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.routemaster.api.auth.domain.user.email.impl.exception.EmailUserErrorDescription;
import org.routemaster.api.auth.domain.user.impl.data.BaseUser;
import org.routemaster.api.auth.domain.user.impl.exception.BaseUserErrorDescription;
import org.routemaster.api.auth.domain.user.impl.exception.UserErrorCode;
import org.routemaster.api.auth.domain.user.impl.persistence.BaseUserRepository;
import org.routemaster.api.auth.domain.user.impl.service.BaseUserService;
import org.routemaster.api.auth.domain.user.impl.util.constant.UserType;
import org.routemaster.api.auth.domain.user.impl.util.mapper.BaseUserMapper;
import org.routemaster.api.auth.domain.user.social.impl.exception.SocialUserErrorDescription;
import org.routemaster.sdk.exception.data.roe.ROEFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class DefaultBaseUserService implements BaseUserService {

    private final BaseUserRepository baseUserRepository;
    private final BaseUserMapper baseUserMapper;

    private final ROEFactory roeFactory;

    @Override
    public BaseUser details(UserType type, String userId) {
        List<String> userIds = new ArrayList<>(Arrays.asList(userId));
        switch (type) {
            case EMAIL_USER: return baseUserRepository.findByEmailUsersIn(userIds)
                .orElseThrow(() -> roeFactory.get(
                    UserErrorCode.ROE_101,
                    EmailUserErrorDescription.EMAIL_USER_NOT_FOUND,
                    HttpStatus.NOT_FOUND));
            case SOCIAL_USER: return baseUserRepository.findBySocialUsersIn(userIds)
                .orElseThrow(() -> roeFactory.get(
                    UserErrorCode.ROE_101,
                    SocialUserErrorDescription.SOCIAL_USER_NOT_FOUND,
                    HttpStatus.NOT_FOUND));
            default: throw roeFactory.get(
                UserErrorCode.ROE_100,
                BaseUserErrorDescription.INVALID_USER_TYPE,
                HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public BaseUser details(String id) {
        return baseUserRepository.findById(id)
            .orElseThrow(() -> roeFactory.get(
                UserErrorCode.ROE_101,
                BaseUserErrorDescription.BASE_USER_NOT_FOUND,
                HttpStatus.NOT_FOUND
            ));
    }

    @Override
    @Transactional
    public BaseUser save(UserType type, String id) {
        try {
            return details(type, id);
        } catch (Exception e) {
            return save(baseUserMapper.register(type, id));
        }
    }

    @Override
    @Transactional
    public BaseUser save(BaseUser baseUser) {
        return baseUserRepository.save(baseUser);
    }

    @Override
    @Transactional
    public BaseUser merge(String standard, String other) {
        BaseUser standardBaseUser = details(standard);
        BaseUser otherBaseUser = details(other);
        delete(other);
        BaseUser baseUser = baseUserMapper.merge(standardBaseUser, otherBaseUser);
        return baseUserRepository.save(baseUser);
    }

    @Override
    public BaseUser merge(BaseUser standard, BaseUser other) {
        if (!baseUserRepository.existsById(standard.getId())
            || !baseUserRepository.existsById(other.getId())) {
            throw roeFactory.get(
                UserErrorCode.ROE_101,
                BaseUserErrorDescription.BASE_USER_NOT_FOUND,
                HttpStatus.NOT_FOUND);
        }
        delete(other.getId());
        BaseUser baseUser = baseUserMapper.merge(standard, other);
        return baseUserRepository.save(baseUser);
    }

    @Override
    @Transactional
    public void delete(String id) {
        if (!baseUserRepository.existsById(id)) {
            throw roeFactory.get(
                UserErrorCode.ROE_101,
                BaseUserErrorDescription.BASE_USER_NOT_FOUND,
                HttpStatus.NOT_FOUND
            );
        }
        baseUserRepository.deleteById(id);
    }
}
