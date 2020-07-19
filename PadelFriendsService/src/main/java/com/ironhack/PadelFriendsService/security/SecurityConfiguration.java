package com.ironhack.PadelFriendsService.security;

import com.ironhack.PadelFriendsService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.savedrequest.NullRequestCache;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserService userService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userService)
                .passwordEncoder(passwordEncoder);
    }

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.httpBasic();

        httpSecurity.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()
                //Club controller
                .mvcMatchers(HttpMethod.GET, "/clubs").hasAnyAuthority("ROLE_ADMIN", "ROLE_PLAYER")
                .mvcMatchers(HttpMethod.POST, "/clubs").hasAuthority("ROLE_ADMIN")
                .mvcMatchers(HttpMethod.GET, "/clubs/{id}").hasAnyAuthority("ROLE_ADMIN", "ROLE_PLAYER")
                .mvcMatchers(HttpMethod.PUT, "/clubs/{id}").hasAuthority("ROLE_ADMIN")
                .mvcMatchers(HttpMethod.DELETE, "/clubs/{id}").hasAuthority("ROLE_ADMIN")
                //Group controller
                .mvcMatchers(HttpMethod.GET, "/groups").hasAnyAuthority("ROLE_ADMIN", "ROLE_PLAYER")
                .mvcMatchers(HttpMethod.POST, "/groups").hasAnyAuthority("ROLE_ADMIN", "ROLE_PLAYER")
                .mvcMatchers(HttpMethod.GET, "/groups/{id}").hasAnyAuthority("ROLE_ADMIN", "ROLE_PLAYER")
                .mvcMatchers(HttpMethod.PUT, "/groups/{id}").hasAnyAuthority("ROLE_ADMIN", "ROLE_PLAYER")
                .mvcMatchers(HttpMethod.DELETE, "/groups/{id}").hasAuthority("ROLE_ADMIN")
                //Reservation controller
                .mvcMatchers(HttpMethod.GET, "/reservations").hasAnyAuthority("ROLE_ADMIN", "ROLE_PLAYER")
                .mvcMatchers(HttpMethod.POST, "/reservations").hasAnyAuthority("ROLE_ADMIN", "ROLE_PLAYER")
                .mvcMatchers(HttpMethod.GET, "/reservations/{id}").hasAnyAuthority("ROLE_ADMIN", "ROLE_PLAYER")
                .mvcMatchers(HttpMethod.PUT, "/reservations/{id}").hasAnyAuthority("ROLE_ADMIN", "ROLE_PLAYER")
                .mvcMatchers(HttpMethod.DELETE, "/reservations/{id}").hasAuthority("ROLE_ADMIN")
                //User controller
                .mvcMatchers(HttpMethod.GET, "/users").hasAnyAuthority("ROLE_ADMIN", "ROLE_PLAYER")
                .mvcMatchers(HttpMethod.GET, "/users/{id}").hasAnyAuthority("ROLE_ADMIN", "ROLE_PLAYER")
                .mvcMatchers(HttpMethod.PUT, "/users/{id}").hasAnyAuthority("ROLE_ADMIN", "ROLE_PLAYER")
                .mvcMatchers(HttpMethod.DELETE, "/users/{id}").hasAuthority("ROLE_ADMIN")
                .and().requestCache().requestCache(new NullRequestCache()).and().httpBasic().and().cors().and().csrf().disable();
    }
}
