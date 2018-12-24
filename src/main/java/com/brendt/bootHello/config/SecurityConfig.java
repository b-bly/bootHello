package com.brendt.bootHello.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		UserBuilder users = User.withDefaultPasswordEncoder();
		auth.inMemoryAuthentication()
			.withUser(users.username("john").password("john").roles("EMPLOYEE"));
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.authorizeRequests()
			.antMatchers("/bower_components/**", "node_modules/**", "/*.js",
					"/*.jsx", "/main.css").permitAll()
			.anyRequest().authenticated()
			.and()
		.formLogin()
			.loginProcessingUrl("/authenticateTheUser")
			.defaultSuccessUrl("/", true)
			.permitAll()
			.and()
		.httpBasic()
			.and()
		.csrf().disable() // don't need?
		.logout()
			.permitAll()
			.logoutSuccessUrl("/");
	}
	
	
}
