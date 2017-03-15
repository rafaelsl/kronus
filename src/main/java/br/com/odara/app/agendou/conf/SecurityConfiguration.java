package br.com.odara.app.agendou.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.odara.app.agendou.daos.EmpresaDAO;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private EmpresaDAO dao;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests()
		.antMatchers("/admin/**").hasRole("ADMIN")
		.antMatchers(HttpMethod.POST, "/admin").hasRole("ADMIN")
		.antMatchers("/user/**").hasRole("USER")
		.antMatchers(HttpMethod.POST, "/user").hasRole("USER")
		.antMatchers("/public/**").permitAll()
		.anyRequest().authenticated()
		.and()
		.formLogin().and()
		.httpBasic();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(dao).passwordEncoder(new BCryptPasswordEncoder());
	}
	
	
}
