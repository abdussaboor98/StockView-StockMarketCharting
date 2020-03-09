package com.cts.training.stockview.zuulapigatewayserver;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	DataSource dataSource;
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
      http.authorizeRequests()
        .antMatchers("/").permitAll() 
        .antMatchers("/user-service/login").access("hasRole('USER','ADMIN')")
        .antMatchers("/user-service/**").access("hasRole('USER')")
        .and().csrf().disable()
        .httpBasic();
    }

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication()
			.usersByUsernameQuery("select username,password,confirmed from users where username=?")
			.authoritiesByUsernameQuery("select username,user_type from users where username=?")
			.dataSource(dataSource)
			.passwordEncoder(new PasswordEncoder() {
				
				@Override
				public boolean matches(CharSequence rawPassword, String encodedPassword) {
					return rawPassword.equals(encodedPassword);
				}
				
				@Override
				public String encode(CharSequence rawPassword) {
					return rawPassword.toString();
				}
			});
	}
}
