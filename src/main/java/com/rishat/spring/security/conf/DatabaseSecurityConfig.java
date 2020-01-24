package com.rishat.spring.security.conf;

import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

@Profile("database")
@EnableWebSecurity
public class DatabaseSecurityConfig extends WebSecurityConfigurerAdapter {

  private DataSource dataSource;

  public DatabaseSecurityConfig(DataSource dataSource) {
    this.dataSource = dataSource;
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.jdbcAuthentication().dataSource(dataSource)
        .usersByUsernameQuery("select username, password, enabled from users WHERE username = ?")
        .authoritiesByUsernameQuery("select username, authority FROM authorities WHERE username = ?")
        .passwordEncoder(new BCryptPasswordEncoder());
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests().anyRequest().hasAnyRole("ADMIN", "USER")
        .and()
        .httpBasic();
  }
}
