package com.management.cni.security;

import com.management.cni.Entity.User;
import com.management.cni.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//to customize Spring Security
@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private UserDetailsService myUserDetailsService;
  @Autowired
  private JwtRequestFilter jwtRequestFilter;
  @Autowired
  private UserService userService;

  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    // TODO configure authentication manager
    auth.userDetailsService(myUserDetailsService);
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder);
  }

  @Override
  @Bean
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }
  //we need to retrieve the correct identity from the database using the provided credentials
  //and then verify it.
  @Bean
  public UserDetailsService userDetailsService() {
    return email -> {
      User user = userService.findUserByEmail(email);
		/*	if(user.isEmpty()) {
				throw new UsernameNotFoundException("No user found with email address: "+email);
			}*/
      return null;
    };
  }

  @Override
	protected void configure(HttpSecurity http) throws Exception {
        // TODO configure web security (public/private URL ..)
		http.cors().and().csrf().disable()
		// The pages does not require login
		.authorizeRequests().antMatchers("/login", "/activation"). permitAll()
		// Set permissions on our private endpoints

		.anyRequest().authenticated().and()
		.exceptionHandling().and().sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		// Add JWT token filter
		http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
		//  We’re doing this because we need access to the user identity
		//at this point to perform authentication/authorization
		//and its extraction happens inside jwtRequestFilter based on the provided JWT token.
	}


}
