package com.mbs.api.config.details;

import com.mbs.api.enums.ProfileRole;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Objects;

/**
 * @author 'Bilol Tuxtamurodov' on 04.04.2026
 * @project Lesson_132
 * @contact @BilolTuxtamurodov
 */

public class EntityDetails {
    public static CustomUserDetails getEntity() {
        return (CustomUserDetails) Objects.requireNonNull(SecurityContextHolder.getContext().getAuthentication()).getPrincipal();
    }

    public static String getProfileId() {
        return getEntity().getId();
    }

    public static ProfileRole getRole() {
        return getEntity().getRole();
    }


}
