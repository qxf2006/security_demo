package com.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.base.service.AuthUserService;
import com.base.service.MyAccessDeniedHandler;
import com.base.service.MyFailureHandler;

@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	AuthUserService authUserService;
	
	@Autowired
	MyFailureHandler mf;
	
	@Autowired
	MyAccessDeniedHandler mad;
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.formLogin().loginPage("/")
		.loginProcessingUrl("/toLogin")
		.defaultSuccessUrl("/seccussUrl")
		.failureHandler(mf)
		.and()
		.authorizeRequests()
		.antMatchers("/","/code/image").permitAll()
		.and()
		.logout()
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		.logoutSuccessUrl("/logoutUrl")
		.permitAll()
		.and()
		.authorizeRequests()
		.antMatchers("/register").permitAll()
		.antMatchers("/toRegister").permitAll()
//		.antMatchers("/work/*").hasRole("work")
		.anyRequest()
		.access("@rbacService.hasPermission(request, authentication)")
//		.authenticated()
		.and()
		.exceptionHandling()
		.accessDeniedHandler(mad)
		.and()
		.csrf().disable();
	}
	
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		 auth.userDetailsService(authUserService);
//	}
	
}
