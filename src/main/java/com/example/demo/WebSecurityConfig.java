package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;
    AuthenticationSuccessHandler successHandler;

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().passwordEncoder(new BCryptPasswordEncoder())
                .dataSource(dataSource)
                .usersByUsernameQuery("select username, password, enabled from user where username=?")
                .authoritiesByUsernameQuery("select username, role from user where username=?")
        ;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()

                .antMatchers("/").access("hasAuthority('User') or hasAuthority('Admin') or hasAuthority('Researcher') or hasAuthority('Investor')")
                .antMatchers("/reasearch","/update","/delete","user/research","/profile").access("hasAuthority('Researcher') or hasAuthority('Admin')")
                .antMatchers("/retrive").access("hasAuthority('Funder') or hasAuthority('Admin')")
                .antMatchers("/admin").access("hasAuthority('Admin')")
                .anyRequest().authenticated()
                .and()
                .csrf().disable()
                .formLogin().successHandler(successHandler)
                .and()
                .httpBasic()
                .and()
                .logout().permitAll();
    }

}
