package com.ivoronline.springboot_security_solution_authentication_builtinclasses.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)  //Enables @Secured & @PreAuthorize
public class MyWebSecurityConfig extends WebSecurityConfigurerAdapter {

  //=================================================================
  // CONFIGURE
  //=================================================================
  @Override
  protected void configure(HttpSecurity httpSecurity) throws Exception {

    //ANONYMOUS ACCESS TO AUTHENTICATION ENDPOINT
    httpSecurity.authorizeRequests().antMatchers("/Authenticate").permitAll();

    //ALLOW ACCESS TO H2 CONSOLE
    httpSecurity.authorizeRequests().antMatchers("/h2-console/**").permitAll();
    httpSecurity.headers().frameOptions().sameOrigin();
    httpSecurity.csrf().disable();

  }

  //=======================================================================
  // PASSWORD ENCODER
  //=======================================================================
  @Bean
  PasswordEncoder passwordEncoder() {
    return NoOpPasswordEncoder.getInstance();
  }

  //=================================================================
  // AUTHENTICATION MANAGER BEAN
  //=================================================================
  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  //=================================================================
  // USER DETAILS SERVICE
  //=================================================================
  @Bean
  @Override
  protected UserDetailsService userDetailsService() {

    //CREATE USERS
    UserDetails myuser  = User.withUsername("myuser" ).password("myuserpassword" ).roles("USER123" ).build();
    UserDetails myadmin = User.withUsername("myadmin").password("myadminpassword").roles("ADMIN").build();

    //STORE USER
    return new InMemoryUserDetailsManager(myuser, myadmin);

  }

}
