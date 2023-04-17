package com.ee417.groupf;


import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class WebSecurityConfig {

    // Setup of data scource
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://ee417.crxkzf89o3fh.eu-west-1.rds.amazonaws.com:3306/GroupF2");
        dataSource.setUsername("EE417");
        dataSource.setPassword("2023_EE417");
        // dataSource.setUrl("jdbc:mysql://localhost:3306/GroupF");
        // dataSource.setUsername("root");
        // dataSource.setPassword("yourpasswd");
        return dataSource;
    }

    // This is setting up how the spring security can comunicate with the database
    // JDBC is being used for this
    @Bean
    public JdbcUserDetailsManager jdbcUserDetailsManager(DataSource dataSource) {
        JdbcUserDetailsManager userDetailsManager = new JdbcUserDetailsManager(dataSource);
        userDetailsManager.setUsersByUsernameQuery("SELECT email, password, enabled FROM user WHERE email = ?");
        userDetailsManager.setAuthoritiesByUsernameQuery("SELECT email, authority FROM user WHERE email = ?");
        return userDetailsManager;
    }

    // This code is setting up the custom loginpage on the url login.html
    // Csrf is disabled
    // When users logout the cookie JSESSIONID is deleted
    // Redirects ar also implemented
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("Menu.html","delivery.html","confirmation.html","orders.html","payment.html","Cart.html","menu.html").authenticated()
                        .anyRequest().permitAll())
                .formLogin()
                .loginPage("/login.html").permitAll()
                .successForwardUrl("/home.html")
                .defaultSuccessUrl("/home.html",true)
                .failureUrl("/login.html")
                .and()
                .logout()
                .logoutUrl("/logout")
                .deleteCookies("JSESSIONID")
                .permitAll()
                .and()
                .csrf().disable()
                .build();
    }
    // For spring security it needs a password encoder
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}