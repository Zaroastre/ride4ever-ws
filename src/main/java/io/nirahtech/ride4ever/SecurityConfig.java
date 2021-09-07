package io.nirahtech.ride4ever;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled=true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    public void globalConfig(AuthenticationManagerBuilder auth, DataSource dataSource) throws Exception {

        // auth.inMemoryAuthentication()
        //         .withUser("user").password("{noop}password").roles("USER")
        //         .and()
        //         .withUser("admin").password("{noop}password").roles("ADMIN");

        auth.jdbcAuthentication()
        .dataSource(dataSource)
        .usersByUsernameQuery("SELECT username AS principal, password AS credentials, true FROM User WHERE username = ?")
        .authoritiesByUsernameQuery("SELECT user_username AS principal, roles_name as role FROM USERS_ROLES WHERE user_username = ?")
        .passwordEncoder(new BCryptPasswordEncoder())
        .rolePrefix("ROLE_");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
        .formLogin().permitAll()
        .and()
        .authorizeRequests().anyRequest().authenticated();
    }
}
