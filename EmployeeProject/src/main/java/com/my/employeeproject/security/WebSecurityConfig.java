package com.my.employeeproject.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.my.employeeproject.service.UserDetailsServiceImpl;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public UserDetailsService userDetailsService() {
		return new UserDetailsServiceImpl();
	}


	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	 DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService());
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		return authenticationProvider;	}
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	 auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder())
		.and()
		.inMemoryAuthentication().withUser("ADMIN").password(passwordEncoder().encode("ADMIN")).roles("ADMIN");
	}
	
	@Bean
	BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers
	("/h2-console/**","/h2-console/login.do**","/h2-console");}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		        .antMatchers("/api/user", "/api/role","/api/welcome","/api/employees/*").hasAuthority("ADMIN")
				.antMatchers(HttpMethod.POST, "/api/employees").hasAuthority("ADMIN")
				.antMatchers(HttpMethod.PUT, "/api/employees").hasAuthority("ADMIN")
				.antMatchers(HttpMethod.DELETE, "/api/employees/*").hasAuthority("ADMIN")
				.anyRequest().authenticated()
				.and().httpBasic() 
				.and()
				.formLogin().loginProcessingUrl("/login").defaultSuccessUrl("/api/welcome",true ).permitAll()				
	    		.and()
	    		.logout().logoutSuccessUrl("/login").permitAll()
	    		.and()
	    		.cors().and().csrf().disable();}

}

	
	
	
	
	
