package com.mbs.api.config.details;

import com.mbs.api.entity.ProfileEntity;
import com.mbs.api.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author 'Bilol Tuxtamurodov' on 02.04.2026
 * @project Lesson_132
 * @contact @BilolTuxtamurodov
 */
@Component
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final ProfileRepository profileRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<ProfileEntity> optional = profileRepository.findByEmailAndVisibleIsTrue(username);
        if (optional.isEmpty()) {
            throw new UsernameNotFoundException("Bunday foydalanuvchi topilmadi");
        }

        return new CustomUserDetails(optional.get());
    }
}
