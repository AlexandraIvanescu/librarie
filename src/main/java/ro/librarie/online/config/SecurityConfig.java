package ro.librarie.online.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;

/**
 * Created by Alexandra Ale on 05.03.2017.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/", "/index.html", "/views/login.html", "/views/register/**", "/components/**", "/fonts/**",
                        "/scripts/**", "/styles/**", "/register/**", "/error/**", "/views/loading.html", "/views/error.html");


    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .antMatcher("/user/**")
                .headers().disable()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/user/**", "/views/user/**").hasAnyAuthority("USER")
                .anyRequest().authenticated()
                .and()
                .exceptionHandling()
                //.authenticationEntryPoint(null)
               // .accessDeniedHandler(null)
                .and()
                .formLogin()
                .loginProcessingUrl("/user/login")
                //.successHandler(null)
                //.failureHandler(null)
                .usernameParameter("email")
                .passwordParameter("password")
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/user/logout")
                .logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler())
                .deleteCookies("JSESSIONID")
                .permitAll();

    }
}

