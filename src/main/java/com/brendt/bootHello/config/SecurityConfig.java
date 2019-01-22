package com.brendt.bootHello.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.brendt.bootHello.security.JwtAuthenticationEntryPoint;
import com.brendt.bootHello.security.JwtAuthenticationFilter;
import com.brendt.bootHello.service.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	UserService userService;

//    @Autowired
//    private DataSource dataSource;
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Autowired
	protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//			UserBuilder users = User.withDefaultPasswordEncoder();
//			auth.inMemoryAuthentication().withUser(users.username("john").password("john").roles("EMPLOYEE"));
			// auth.jdbcAuthentication().dataSource(dataSource);

        auth
        	.userDetailsService(userService)
            .passwordEncoder(passwordEncoder());	    
	}
    
    @Autowired
    private JwtAuthenticationEntryPoint unauthorizedHandler;

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }

    
    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable()
			.cors()
				.and()
            .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
            .exceptionHandling()
                .authenticationEntryPoint(unauthorizedHandler)
                .and()
			.authorizeRequests()
				.antMatchers("/bower_components/**", "node_modules/**", "/*.js", "/*.jsx", "/main.css", "index.html")
					.permitAll()
                    .antMatchers("/",
                            "/favicon.ico",
                            "/**/*.png",
                            "/**/*.gif",
                            "/**/*.svg",
                            "/**/*.jpg",
                            "/**/*.html",
                            "/**/*.css",
                            "/**/*.js")
                            .permitAll()
                        .antMatchers("/api/auth/**", "/signin/**")
                            .permitAll()
                        .antMatchers("/error").permitAll()
                        .antMatchers("/api/user/checkUsernameAvailability", "/api/user/checkEmailAvailability")
                            .permitAll()
                        .antMatchers(HttpMethod.GET, "/api/users/**")
                            .permitAll()                   
                        .anyRequest()
                            .authenticated()
							.and()
			
//						.formLogin()
//							.loginProcessingUrl("/authenticateTheUser")
//							.defaultSuccessUrl("/", true)
//							.and()
							
						.httpBasic()
							.and()
						.logout()
							.logoutSuccessUrl("/");
        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

	}
}

