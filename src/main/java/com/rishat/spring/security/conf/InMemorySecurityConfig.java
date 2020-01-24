package com.rishat.spring.security.conf;

import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Profile("inMemory")
@EnableWebSecurity
public class InMemorySecurityConfig extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {

    auth.inMemoryAuthentication()
        .withUser("admin")
        .password("{noop}admin")
        .roles("ADMIN")
        .and()
        .withUser("user")
        .password("{noop}user")
        .credentialsExpired(true)
        .accountExpired(true)
        .accountLocked(true)
        .roles("USER");
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests().anyRequest().hasAnyRole("ADMIN", "USER")
        .and()
        .httpBasic();
  }
}
