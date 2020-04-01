package com.feijo.financj.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.feijo.financj.security.JWTAuthenticationFilter;
import com.feijo.financj.security.JWTAuthorizationFilter;
import com.feijo.financj.security.JWTUtil;

import net.bytebuddy.build.Plugin.Engine.Source.Origin;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private Environment env;
	
	@Autowired
	private JWTUtil jwtUtil;
	
	/*private static final String[] PUBLIC_MATCHERS = {
		//"/h2-console/**"
		"/**"
	};
	
	private static final String[] PUBLIC_MATCHERS_POST = {
		"/usuarios/**"
	};*/
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		if (Arrays.asList(env.getActiveProfiles()).contains("test")) {
			http.headers().frameOptions().disable();
		}
		
		http.cors();
		http.authorizeRequests()
			/*.antMatchers(HttpMethod.POST, PUBLIC_MATCHERS_POST).permitAll()
		    .antMatchers(PUBLIC_MATCHERS).permitAll()*/
			.antMatchers("/**").permitAll()
		    .anyRequest()
		    .authenticated();
		http.csrf().disable();
		/*http.addFilter(new JWTAuthenticationFilter(authenticationManager(), jwtUtil));
		http.addFilter(new JWTAuthorizationFilter(authenticationManager(), jwtUtil, userDetailsService)); 
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);*/
		
		super.configure(http);
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
	}	
	
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource(); 	
		
		CorsConfiguration cors = new CorsConfiguration().applyPermitDefaultValues();
		
		cors.addAllowedMethod(HttpMethod.PUT);
		cors.addAllowedMethod(HttpMethod.DELETE);
		
		source.registerCorsConfiguration("/**", cors);
		
		return source;
	}
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
