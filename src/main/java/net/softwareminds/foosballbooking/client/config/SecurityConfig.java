package net.softwareminds.foosballbooking.client.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

//Needed to have the 'springSecurityFilterChain' filter for the OAuth2RestTemplate
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    // no user authentication is needed
    http.authorizeRequests()
        .anyRequest()
        .permitAll();
  }
}
