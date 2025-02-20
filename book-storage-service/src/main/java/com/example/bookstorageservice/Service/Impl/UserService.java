package com.example.bookstorageservice.Service.Impl;

import com.example.bookstorageservice.Model.User;
import com.example.bookstorageservice.Repository.UserRepository;
import lombok.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(username).orElseThrow(() -> new UsernameNotFoundException(
                String.format("User '%s' not found", username)
        ));

        return UserDetailsImpl.build(user);
    }
}
