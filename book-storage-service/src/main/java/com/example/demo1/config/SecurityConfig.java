    package com.example.demo1.config;

    import com.example.demo1.Service.Impl.UserService;
    import org.springframework.context.annotation.Bean;
    import org.springframework.context.annotation.Configuration;
    import org.springframework.http.HttpStatus;
    import org.springframework.security.authentication.AuthenticationManager;
    import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
    import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
    import org.springframework.security.config.annotation.web.builders.HttpSecurity;
    import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
    import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
    import org.springframework.security.config.http.SessionCreationPolicy;
    import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
    import org.springframework.security.crypto.password.PasswordEncoder;
    import org.springframework.security.web.SecurityFilterChain;
    import org.springframework.security.web.authentication.HttpStatusEntryPoint;
    import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
    import org.springframework.web.cors.CorsConfiguration;

    @Configuration
    @EnableWebSecurity
    public class    SecurityConfig {
        private  UserService userService;
        private  TokenFilter tokenFilter;

        public SecurityConfig(UserService userService, TokenFilter tokenFilter){
            this.userService = userService;
            this.tokenFilter = tokenFilter;
        }

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
            http
                    .csrf(AbstractHttpConfigurer::disable)
                    .cors(corsConfigurer -> corsConfigurer.configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues()))
                    .exceptionHandling(exceptions -> exceptions
                            .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)))
                    .sessionManagement(session -> session
                            .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                    .authorizeHttpRequests(authorize -> authorize
                            .requestMatchers("/api/auth/**").permitAll()
                            .requestMatchers("/api/books").authenticated()
                            .anyRequest().permitAll())
                    .addFilterBefore(tokenFilter, UsernamePasswordAuthenticationFilter.class);
            return http.build();
        }

        @Bean
        public PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }

        @Bean
        public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
            return authenticationConfiguration.getAuthenticationManager();
        }
    }
    //
    //package com.example.demo1.config;
    //
    //import com.example.demo1.utils.JwtFilter;
    //import org.springframework.context.annotation.Bean;
    //import org.springframework.context.annotation.Configuration;
    //import org.springframework.http.HttpStatus;
    //import org.springframework.security.authentication.AuthenticationManager;
    //import org.springframework.security.authentication.ProviderManager;
    //import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
    //import org.springframework.security.config.annotation.web.builders.HttpSecurity;
    //import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
    //import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
    //import org.springframework.security.config.http.SessionCreationPolicy;
    //import org.springframework.security.core.userdetails.User;
    //import org.springframework.security.core.userdetails.UserDetails;
    //import org.springframework.security.core.userdetails.UserDetailsService;
    //import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
    //import org.springframework.security.provisioning.InMemoryUserDetailsManager;
    //import org.springframework.security.web.SecurityFilterChain;
    //import org.springframework.security.web.authentication.HttpStatusEntryPoint;
    //import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
    //import org.springframework.web.cors.CorsConfiguration;
    //
    //import java.util.List;
    //
    //@Configuration
    //@EnableWebSecurity
    //public class SecurityConfig {
    //
    //    private final JwtFilter jwtFilter;
    //
    //    public SecurityConfig(JwtFilter jwtFilter) {
    //        this.jwtFilter = jwtFilter;
    //    }
    //
    //    @Bean
    //    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    //        http
    //                .csrf(AbstractHttpConfigurer::disable)
    //                .cors(corsConfigurer -> corsConfigurer.configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues()))
    //                .exceptionHandling(exceptions -> exceptions
    //                        .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)))
    //                .sessionManagement(session -> session
    //                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
    //                .authorizeHttpRequests(authorize -> authorize
    //                        .requestMatchers("/api/auth/**").permitAll()
    //                        .requestMatchers("/api/books/addBook").fullyAuthenticated()
    //                        .anyRequest().permitAll())
    //                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    //        return http.build();
    //    }
    //
    //    @Bean
    //    public UserDetailsService userDetailsService() {
    //        UserDetails user = User.withUsername("user")
    //                .password("{noop}password")
    //                .roles("USER")
    //                .build();
    //        return new InMemoryUserDetailsManager(user);
    //    }
    //
    //    @Bean
    //    public BCryptPasswordEncoder passwordEncoder() {
    //        return new BCryptPasswordEncoder();
    //    }
    //
    //    @Bean
    //    public AuthenticationManager authenticationManager(UserDetailsService userDetailsService) {
    //        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
    //        authProvider.setUserDetailsService(userDetailsService);
    //        authProvider.setPasswordEncoder(passwordEncoder());
    //        return new ProviderManager(List.of(authProvider));
    //    }
    //}
