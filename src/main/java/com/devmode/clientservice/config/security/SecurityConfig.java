package com.devmode.clientservice.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final OidcUserService oidcUserService;

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable().antMatcher("/**").authorizeRequests()
                .antMatchers("/", "/index.html", "/logout", "/login", "/providers/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .oauth2Login().userInfoEndpoint().oidcUserService(oidcUserService)
                .and().permitAll()
                .and().
                logout().logoutSuccessUrl("/");
    }
}
