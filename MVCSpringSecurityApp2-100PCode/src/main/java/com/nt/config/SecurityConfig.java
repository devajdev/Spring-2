package com.nt.config;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

@Configuration
@EnableWebMvcSecurity
public class SecurityConfig  extends WebSecurityConfigurerAdapter{
	@Autowired
	private DataSource ds;
	
	/*@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(ds).usersByUsernameQuery("SELECT UNAME,PASSWORD,STATUS FROM USERS WHERE UNAME=?")
		.authoritiesByUsernameQuery("SELECT ROLE_ID,ROLE,UNAME FROM USER_ROLES WHERE UNAME=?");
	}
	*/
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		List<GrantedAuthority> roleList;
		roleList=new ArrayList();
		roleList.add(new SimpleGrantedAuthority("ROLE_OWNER"));
		auth.inMemoryAuthentication().getUserDetailsService().createUser(new User("raja","raja", true, true, true, true,roleList));
	}
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/home.htm").permitAll().antMatchers("/inbox.htm").access("hasAnyRole('ROLE_OWNER','ROLE_ADMIN')")
		.and().formLogin().and().logout().logoutUrl("/our_logout_url").logoutSuccessUrl("/logout.jsp").and().exceptionHandling().accessDeniedPage("/access_failed.jsp")
		.and().rememberMe();
	}
	
	

}
