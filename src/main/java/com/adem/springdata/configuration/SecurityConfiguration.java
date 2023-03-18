package com.adem.springdata.configuration;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter
{
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private DataSource dataSource;

	@Value("${spring.queries.users-query}")
	private String usersQuery;

	@Value("${spring.queries.roles-query}")
	private String rolesQuery;

	@Override // 1) Authentication
	protected void configure(AuthenticationManagerBuilder auth) throws Exception
	{
		auth.
			jdbcAuthentication()
			.usersByUsernameQuery(usersQuery)
			.authoritiesByUsernameQuery(rolesQuery)
			.dataSource(dataSource)
			.passwordEncoder(bCryptPasswordEncoder);
	}

	@Override // 2) Autorisation
	protected void configure(HttpSecurity http) throws Exception
	{

		http.
			authorizeRequests()
			.antMatchers("/").permitAll() // Access for all users
			.antMatchers("/login").permitAll() // Access for all users
			.antMatchers("/registration").permitAll() // Access for all users
			.antMatchers("/role/**").permitAll()
			.antMatchers("/accounts/**").permitAll()
			.antMatchers("/provider/**").hasAnyAuthority("ADMIN", "SUPERADMIN")
			.antMatchers("/article/**").hasAnyAuthority("USER", "SUPERADMIN").anyRequest()
			.authenticated().and().csrf().disable().formLogin() // The access is through a form

			.loginPage("/login").failureUrl("/login?error=true") // Set the login page

			.defaultSuccessUrl("/home") // Home page after login successfully
			.usernameParameter("email") // Login and password authentication settings
			.passwordParameter("password")
			.and().logout()
			.logoutRequestMatcher(new AntPathRequestMatcher("/logout")) // Disconnection pathway here /logout
			.logoutSuccessUrl("/login").and().exceptionHandling() // Once logged back to login

			.accessDeniedPage("/403");
	}

	// Allow access to resources
	@Override
	public void configure(WebSecurity web) throws Exception
	{
		web
			.ignoring()
			.antMatchers("/front/**","/images/**","/css/**");
	}

}
