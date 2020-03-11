package com.cts.training.stockview.zuulapigatewayserver;

import java.util.Arrays;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	DataSource dataSource;
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
      http.cors().and().csrf().disable()
      	.authorizeRequests()
        .antMatchers("/").permitAll() 
        .antMatchers("/user-service/login").permitAll()
        .antMatchers("/user-service/users/**").hasAnyRole("USER,ADMIN")
        .antMatchers("/company-service/**").hasAnyRole("USER,ADMIN")
        .antMatchers("/initial-public-offering-service/**").hasAnyRole("USER,ADMIN")
        .antMatchers("/sector-service/**").hasAnyRole("USER,ADMIN")
        .antMatchers("/stock-exchange-service/**").hasAnyRole("USER,ADMIN")
        .antMatchers("/stock-price-service/**").hasAnyRole("USER,ADMIN")
        .and()
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
	
	@Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("OPTIONS");
        config.addAllowedMethod("GET");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("DELETE");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}
