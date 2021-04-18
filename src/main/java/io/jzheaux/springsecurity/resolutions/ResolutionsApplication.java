package io.jzheaux.springsecurity.resolutions;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

import static org.springframework.http.HttpMethod.*;

@SpringBootApplication
public class ResolutionsApplication extends WebSecurityConfigurerAdapter {

  public static void main(String[] args) {
    SpringApplication.run(ResolutionsApplication.class, args);
  }

  @Bean
  public UserDetailsService userDetailsService(UserRepository users) {
    return new UserRepositoryUserDetailsService(users);
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests(authz -> authz
        .mvcMatchers(GET, "resolutions", "resolutions/**").hasAnyAuthority("resolution:read")
        .anyRequest().hasAnyAuthority("resolution:write"))
        .httpBasic(basic -> {
        });
  }
}
