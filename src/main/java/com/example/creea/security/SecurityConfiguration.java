package com.example.creea.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(jsr250Enabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final CustomUserDetailsService customUserDetailsService;

    public SecurityConfiguration(CustomUserDetailsService userDetailsService) {
        this.customUserDetailsService = userDetailsService;
    }


    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/animal").hasAnyAuthority(
                        "CUSTOMER")
                .antMatchers("/user").hasAnyAuthority("CUSTOMER")
                .antMatchers("/animal/**").hasAnyAuthority("CUSTOMER")
                .antMatchers("/admin/**").hasAnyAuthority("ADMIN")
                .antMatchers("/myanimals").hasAnyAuthority("CUSTOMER")
                .antMatchers("/account").hasAnyAuthority("CUSTOMER")
                .antMatchers("/user/login", "/signUp").permitAll()
                .and().httpBasic();

    }
}
