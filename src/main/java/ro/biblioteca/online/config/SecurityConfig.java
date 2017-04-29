package ro.biblioteca.online.config;

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
import ro.biblioteca.online.services.LibraryDetailsService;

/**
 * Created by Alexandra Ale on 05.03.2017.
 */

@Configuration
@EnableWebSecurity
class SecurityConfig extends WebSecurityConfigurerAdapter {

    private LibraryDetailsService libraryDetailsService;
    private RestUnauthorizedEntryPoint restAuthenticationEntryPoint;
    private RestAccessDeniedHandler restAccessDeniedHandler;
    private RestAuthenticationFailureHandler restAuthenticationFailureHandler;
    private RestAuthenticationSuccessHandler restAuthenticationSuccessHandler;

    @Autowired
    public SecurityConfig(LibraryDetailsService libraryDetailsService, RestUnauthorizedEntryPoint restAuthenticationEntryPoint,
                          RestAccessDeniedHandler restAccessDeniedHandler, RestAuthenticationFailureHandler restAuthenticationFailureHandler,
                          RestAuthenticationSuccessHandler restAuthenticationSuccessHandler) {

        this.libraryDetailsService = libraryDetailsService;
        this.restAuthenticationEntryPoint = restAuthenticationEntryPoint;
        this.restAccessDeniedHandler = restAccessDeniedHandler;
        this.restAuthenticationFailureHandler = restAuthenticationFailureHandler;
        this.restAuthenticationSuccessHandler = restAuthenticationSuccessHandler;
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(libraryDetailsService).passwordEncoder(passwordEncoder());
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
                .antMatcher("/library/**")
                .headers().disable()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/library/**").hasAnyAuthority("USER")
                .anyRequest().authenticated()
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(restAuthenticationEntryPoint)
                .accessDeniedHandler(restAccessDeniedHandler)
                .and()
                .formLogin()
                .loginProcessingUrl("/library/login")
                .successHandler(restAuthenticationSuccessHandler)
                .failureHandler(restAuthenticationFailureHandler)
                .usernameParameter("email")
                .passwordParameter("password")
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/library/logout")
                .logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler())
                .deleteCookies("JSESSIONID")
                .permitAll();

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}

