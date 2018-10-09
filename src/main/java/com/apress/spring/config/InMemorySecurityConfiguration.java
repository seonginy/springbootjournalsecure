package com.apress.spring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;

/*
@Configuration : 스프링 부트에서 구성 클래스로 쓸 클래스임을 밝힘.
@EnableGlobalAuthentication : 이 애너테이션을 붙인 클래스는 AuthenticationManagerBuilder의 전역 인스턴스를 구성하며 앱에 있는 모든 빈에 보안을 적용합니다.
 */

@Configuration
@EnableGlobalAuthentication
public class InMemorySecurityConfiguration {

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("user").password("{noop}password").roles("USER").and()
                                     .withUser("admin").password("{noop}password").roles("USER","ADMIN");
    }
}
