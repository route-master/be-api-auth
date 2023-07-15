package org.routemaster.api.auth.domain.user.social.impl.service.impl;

import java.util.Set;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.routemaster.api.auth.domain.user.impl.exception.UserErrorCode;
import org.routemaster.api.auth.domain.user.social.impl.data.SocialUser;
import org.routemaster.api.auth.domain.user.social.impl.exception.SocialUserErrorDescription;
import org.routemaster.api.auth.domain.user.social.impl.persistence.SocialUserRepository;
import org.routemaster.api.auth.domain.user.social.impl.service.SocialUserService;
import org.routemaster.api.auth.domain.user.social.impl.util.mapper.SocialUserMapper;
import org.routemaster.sdk.exception.data.roe.ROEFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class DefaultSocialUserService implements SocialUserService {

    private final SocialUserRepository socialUserRepository;
    private final SocialUserMapper socialUserMapper;

    private final ROEFactory roeFactory;

    @Override
    public SocialUser details(String id) {
        return socialUserRepository.findById(id)
            .orElseThrow(() -> roeFactory.get(
                UserErrorCode.ROE_101,
                SocialUserErrorDescription.SOCIAL_USER_NOT_FOUND,
                HttpStatus.NOT_FOUND
            ));
    }

    @Override
    public SocialUser details(String provider, Long socialId) {
        return socialUserRepository.findByProviderAndSocialId(provider, socialId)
            .orElseThrow(() -> roeFactory.get(
                UserErrorCode.ROE_101,
                SocialUserErrorDescription.SOCIAL_USER_NOT_FOUND,
                HttpStatus.NOT_FOUND
            ));
    }

    @Override
    @Transactional
    public SocialUser register(String provider, Long socialId, Set<String> authorities) {
        if (socialUserRepository.existsByProviderAndSocialId(provider, socialId)) {
            throw roeFactory.get(
                UserErrorCode.ROE_100,
                SocialUserErrorDescription.SOCIAL_USER_ALREADY_EXIST,
                HttpStatus.BAD_REQUEST
            );
        }
        SocialUser socialUser = socialUserMapper.register(provider, socialId, authorities);
        return socialUserRepository.save(socialUser);
    }

    @Override
    @Transactional
    public SocialUser updateRefreshToken(String id, String refreshToken) {
        SocialUser socialUser = details(id);
        socialUser.setRefreshToken(refreshToken);
        return socialUserRepository.save(socialUser);
    }

    @Override
    @Transactional
    public SocialUser updateRefreshToken(String provider, Long socialId, String refreshToken) {
        SocialUser socialUser = details(provider, socialId);
        socialUser.setRefreshToken(refreshToken);
        return socialUserRepository.save(socialUser);
    }

    @Override
    @Transactional
    public void delete(String id) {
        if (!socialUserRepository.existsById(id)) {
            throw roeFactory.get(
                UserErrorCode.ROE_101,
                SocialUserErrorDescription.SOCIAL_USER_NOT_FOUND,
                HttpStatus.NOT_FOUND
            );
        }
        socialUserRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void delete(String provider, Long socialId) {
        if (!socialUserRepository.existsByProviderAndSocialId(provider, socialId)) {
            throw roeFactory.get(
                UserErrorCode.ROE_101,
                SocialUserErrorDescription.SOCIAL_USER_NOT_FOUND,
                HttpStatus.NOT_FOUND
            );
        }
        socialUserRepository.deleteByProviderAndSocialId(provider, socialId);
    }
}
