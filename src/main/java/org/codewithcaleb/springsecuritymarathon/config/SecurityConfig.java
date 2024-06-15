package org.codewithcaleb.springsecuritymarathon.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
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
     *
     *  http.httpBasic().and().formLogin(); (adds a log in form before your routes
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

    @Bean
    public UserDetailsService userDetailsService(){

        // i am adding my user in the userDetails Service
        //so that i returns my user when the provider is calling it
        UserDetails user = User.builder()
                .username("caleb")
                .password("password")
                .authorities("read") //used for authorization
                .build();

        //i am not fetching my user from the DB and i need to remember them
       return new InMemoryUserDetailsManager(user);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        //not recommended in production,
        //i should use something like bcrypt
        return NoOpPasswordEncoder.getInstance();
    }
}
