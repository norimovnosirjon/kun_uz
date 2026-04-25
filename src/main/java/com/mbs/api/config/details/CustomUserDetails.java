package com.mbs.api.config.details;

import com.mbs.api.entity.ProfileEntity;
import com.mbs.api.enums.ProfileRole;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author 'Bilol Tuxtamurodov' on 02.04.2026
 * @project Lesson_132
 * @contact @BilolTuxtamurodov
 */

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomUserDetails implements UserDetails {
    String id;
    String name;
    String surname;
    String phone;
    String email;
    ProfileRole role;
    String password;


    public CustomUserDetails(ProfileEntity entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.surname = entity.getSurname();
        this.phone = entity.getPhone();
        this.role = entity.getRole();
        this.email = entity.getEmail();
        this.password = entity.getPassword();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> list = new ArrayList<>();
        list.add(new SimpleGrantedAuthority(role.name()));
        return list;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

}
