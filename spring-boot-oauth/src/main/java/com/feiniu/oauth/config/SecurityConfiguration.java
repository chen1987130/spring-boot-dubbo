package com.feiniu.oauth.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Bean
	@Override
	protected UserDetailsService userDetailsService() {
		Collection<UserDetails> list = new ArrayList<UserDetails>();
		list.add(new User("user_1", "123456", Arrays.asList(new SimpleGrantedAuthority("STUDENTS"))));
		list.add(new User("user_2", "123456", Arrays.asList(new SimpleGrantedAuthority("TEACHER"))));
		return new InMemoryUserDetailsManager(list);
	}

	// @Override
	// protected void configure(HttpSecurity http) throws Exception {
	// http.requestMatchers().anyRequest()
	// .and().authorizeRequests().antMatchers("/oauth/*").permitAll();
	// }
}