package org.codewithcaleb.springsecuritymarathon.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

//Configuration classes are where i override,the autoconfiguration settings for my dependencies
@Configuration
public class SecurityConfig {

    /***
     * The http object is actually a builder With it we build the security filter chain with ends
     *  up as a security filter in the filter chain (we configure
     *  (a)Authentication
     *  (b)Authorization
     *  (c)CSRF and CORS Configs

     //Configuring Authentication

     //syntax one
     * http.httpBasic(c->c.realmName("my_real_name"));/

     /**Syntax two
     * http.httpBasic().realmName("my_real_name");
     *
     * Authorization pattern
     * (MatcherMethod).(the Rule)
     * .anyRequest().authenticated() -> for all the requests someone needs to have authenticated before.
     *
     * we can also merge authentication and authorization rules
     *   http.httpBasic()
     *        .and()
     *        .authorizeHttpRequests()
     *       .anyRequest().authenticated()
     *
     *  It is better to separate your authentication/authorization configs to make them easier to read.
     *  (However, if you want to nest, please use indentation.
     *
     *  We should be careful when searching for answers in the internet,at times, we do not find real answers,
     *  but rather find, workarounds.
     **/
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        //authentication
        http.httpBasic().and().formLogin();


        //authorization
        http.authorizeHttpRequests()
                .anyRequest().authenticated();

        return http.build();
    }
}
