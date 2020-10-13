package com.ljy.market.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;

import com.ljy.market.user.UserService;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private DefaultOAuth2UserService oauth2UserService;
	
	@Autowired
	private UserService userService;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.userDetailsService(userService)
			.passwordEncoder(passwordEncoder());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
			.antMatchers("/h2/**").permitAll()
			.and().csrf().disable()
			.headers().frameOptions().disable()
			.and()
			.formLogin()
			.loginPage("/loginForm")
			.loginProcessingUrl("/login")
			.and()
			.oauth2Login()
			.loginPage("/loginForm")
			.userInfoEndpoint()
			.userService(oauth2UserService);
	}
}
