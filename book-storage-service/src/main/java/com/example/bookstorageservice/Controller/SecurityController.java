package com.example.bookstorageservice.Controller;

import com.example.bookstorageservice.Constants.*;
import com.example.bookstorageservice.DTO.*;
import com.example.bookstorageservice.Exception.*;
import com.example.bookstorageservice.Model.User;
import com.example.bookstorageservice.Repository.UserRepository;
import com.example.bookstorageservice.Utils.JwtCore;
import lombok.*;
import org.springframework.http.*;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class SecurityController {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtCore jwtCore;

    @PostMapping("/signup")
    ResponseEntity<?> signup(@RequestBody SignupRequest signupRequest){
        if(userRepository.existsByUsername(signupRequest.getUsername())){
            throw new UsernameAlreadyExistsException(ErrorMessages.ERROR_USERNAME_EXISTS);
        }
        User user = new User();
        user.setUsername(signupRequest.getUsername());
        user.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
        userRepository.save(user);
        return ResponseEntity.status(HttpStatusConstants.CREATED_STATUS).body(user);
    }

    @PostMapping("/signin")
    ResponseEntity<?> signin(@RequestBody SigninRequest signinRequest){
        Authentication authentication = null;
        try{
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(signinRequest.getUsername(), signinRequest.getPassword()));
        }catch (BadCredentialsException e){
            return ResponseEntity.status(HttpStatusConstants.USER_UNAUTHORIZED).body("Unauthorized");
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtCore.generateToken(authentication);
        return ResponseEntity.ok(jwt);
    }
}
