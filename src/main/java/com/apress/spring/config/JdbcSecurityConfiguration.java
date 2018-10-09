package com.apress.spring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.sql.ResultSet;

@Configuration
@EnableGlobalAuthentication
public class JdbcSecurityConfiguration extends GlobalAuthenticationConfigurerAdapter {

    @Bean
    public UserDetailsService userDetailsService(JdbcTemplate jdbcTemplate) {

        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

        RowMapper<User> userRowMapper = (ResultSet rs, int i) ->
                new User(
                        rs.getString("ACCOUNT_NAME"),
                        encoder.encode(rs.getString("PASSWORD")),
                        rs.getBoolean("ENABLED"),
                        rs.getBoolean("ENABLED"),
                        rs.getBoolean("ENABLED"),
                        rs.getBoolean("ENABLED"),
                        AuthorityUtils.createAuthorityList("ROLE_USER", "ROW_ADMIN")
                );
        return username ->
                jdbcTemplate.queryForObject("SELECT * from ACCOUNT where ACCOUNT_NAME = ?", userRowMapper, username);
    }

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public void init(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(this.userDetailsService);
    }
}
