package ro.librarie.online.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ro.librarie.online.services.UserDetailsService;

/**
 * Created by Alexandra Ale on 05.03.2017.
 */

@Configuration
@EnableWebSecurity
class SecurityConfig extends WebSecurityConfigurerAdapter {

    private UserDetailsService userDetailsService;
    private RestUnauthorizedEntryPoint restAuthenticationEntryPoint;
    private RestAccessDeniedHandler restAccessDeniedHandler;
    private RestAuthenticationFailureHandler restAuthenticationFailureHandler;
    private RestAuthenticationSuccessHandler restAuthenticationSuccessHandler;

    @Autowired
    public SecurityConfig(UserDetailsService userDetailsService, RestUnauthorizedEntryPoint restAuthenticationEntryPoint,
                          RestAccessDeniedHandler restAccessDeniedHandler, RestAuthenticationFailureHandler restAuthenticationFailureHandler,
                          RestAuthenticationSuccessHandler restAuthenticationSuccessHandler) {

        this.userDetailsService = userDetailsService;
        this.restAuthenticationEntryPoint = restAuthenticationEntryPoint;
        this.restAccessDeniedHandler = restAccessDeniedHandler;
        this.restAuthenticationFailureHandler = restAuthenticationFailureHandler;
        this.restAuthenticationSuccessHandler = restAuthenticationSuccessHandler;
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/", "/index.html", "/components/**", "/fonts/**", "/register/**", "/error/**",
                        "/security/**", "/loading/**", "/login/**", "/error/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .antMatcher("/user/**")
                .headers().disable()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/user/**", "/librarie/**", "/book-list/**").hasAnyAuthority("USER")
                .anyRequest().authenticated()
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(restAuthenticationEntryPoint)
                .accessDeniedHandler(restAccessDeniedHandler)
                .and()
                .formLogin()
                .loginProcessingUrl("/user/login")
                .successHandler(restAuthenticationSuccessHandler)
                .failureHandler(restAuthenticationFailureHandler)
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

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}

