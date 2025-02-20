package com.example.bookstorageservice.Service.Impl;

import com.example.bookstorageservice.Model.User;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@AllArgsConstructor
@RequiredArgsConstructor
public class UserDetailsImpl implements UserDetails{
    private String username;
    private String password;

    public static UserDetailsImpl build(User user){
        return new UserDetailsImpl(
                user.getUsername(),
                user.getPassword()
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
