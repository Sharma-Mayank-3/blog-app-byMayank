package com.blog.byMayank.config;

import com.blog.byMayank.security.CustomUserDetailService;
import com.blog.byMayank.security.JwtAuthentationEntryPoint;
import com.blog.byMayank.security.JwtAuthentationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private CustomUserDetailService customUserDetailService;

    @Autowired
    private JwtAuthentationEntryPoint jwtAuthentationEntryPoint;

    @Autowired
    private JwtAuthentationFilter jwtAuthentationFilter;

// // basic auth
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity
//                .csrf(csrf -> csrf.disable())
////                .authorizeHttpRequests(authorize -> authorize
////                        .requestMatchers("/blog-app/user/**").permitAll().requestMatchers("/blog-app/post/**").permitAll().anyRequest().authenticated()
////                )
//			.authorizeHttpRequests(authorize -> authorize
//                .anyRequest().authenticated()
//               )
//                .httpBasic(Customizer.withDefaults());
//
//        // take it from DB.
//        httpSecurity.authenticationProvider(daoAuthenticationProvider());
//
//        return httpSecurity.build();
//    }


    // Jwt Auth
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(csrf -> csrf.disable())
                .cors(cors-> cors.disable())
                .authorizeHttpRequests(auth -> auth.requestMatchers("/blog-app/v1/auth/login").permitAll().requestMatchers("/blog-app/user/create").permitAll().anyRequest().authenticated())
                .exceptionHandling(ex -> ex.authenticationEntryPoint(jwtAuthentationEntryPoint))
                        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
//

        // take it from DB.
        httpSecurity.authenticationProvider(daoAuthenticationProvider());
        httpSecurity.addFilterAfter(jwtAuthentationFilter, UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(this.customUserDetailService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
