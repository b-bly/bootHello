package com.brendt.bootHello.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//			UserBuilder users = User.withDefaultPasswordEncoder();
//			auth.inMemoryAuthentication().withUser(users.username("john").password("john").roles("EMPLOYEE"));
		auth.jdbcAuthentication().dataSource(dataSource);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/bower_components/**", "node_modules/**", "/*.js", "/*.jsx", "/main.css", "index.html")
				.permitAll().antMatchers("/", "/api/user").permitAll().antMatchers("/api/**").authenticated().and()
				.formLogin().loginProcessingUrl("/authenticateTheUser").defaultSuccessUrl("/", true).permitAll().and()
				.httpBasic().and().csrf().disable() // don't need?
				.logout().permitAll().logoutSuccessUrl("/");
	}

//	@Override
//	public UserDetailsService userDetailsService() {
//		UserDetails user = User.withDefaultPasswordEncoder().username("user").password("password").roles("USER")
//				.build();
//
//		return new InMemoryUserDetailsManager(user);
//	}
}
