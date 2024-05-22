package com.solumesl.aims.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import com.solumesl.aims.security.filter.BearerTokenFilter;

 @Configuration
 @EnableWebSecurity
public class AimsLicenseSecurityConfig  {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf().disable()
		.exceptionHandling()
		.and() 
		.sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
		.addFilterBefore(authenticationFilter(http), AnonymousAuthenticationFilter.class)
		.formLogin().disable()
		.logout().disable();
		http.httpBasic().disable();
		return http.build();
	}

	private static final RequestMatcher PROTECTED_URLS = new OrRequestMatcher(
			new AntPathRequestMatcher("/api/**")
			);
	private AuthenticationProvider provider;

	public AimsLicenseSecurityConfig(final AuthenticationProvider authenticationProvider) {
		super();
		this.provider = authenticationProvider;
	}

	@Bean
	public AuthenticationManager authManager(HttpSecurity http) throws Exception {
		AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
		authenticationManagerBuilder.authenticationProvider(provider);
		return authenticationManagerBuilder.build();
	}

 
	@Bean
	BearerTokenFilter authenticationFilter(HttpSecurity http) throws Exception {
		final BearerTokenFilter filter = new BearerTokenFilter(PROTECTED_URLS);
		filter.setAuthenticationManager(authManager(http));
		return filter;
	}

 

}
