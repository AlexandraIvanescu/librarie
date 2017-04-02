package ro.librarie.online.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ro.librarie.online.services.AdminDetailsService;
import ro.librarie.online.services.UserDetailsService;

/**
 * Created by Alexandra Ale on 05.03.2017.
 */

@Configuration
@EnableWebSecurity
class SecurityConfiguration {

    @Configuration
    @Order(1)
    public static class AdminSecurityConfiguration extends WebSecurityConfigurerAdapter {

        private AdminDetailsService adminDetailsService;
        private PasswordEncoder passwordEncoder;
        private RestUnauthorizedEntryPoint restAuthenticationEntryPoint;
        private RestAccessDeniedHandler restAccessDeniedHandler;
        private RestAuthenticationFailureHandler restAuthenticationFailureHandler;
        private AdminRestAuthenticationSuccessHandler restAuthenticationSuccessHandler;

        @Autowired
        public AdminSecurityConfiguration(AdminDetailsService adminDetailsService, PasswordEncoder passwordEncoder, RestUnauthorizedEntryPoint restAuthenticationEntryPoint, RestAccessDeniedHandler restAccessDeniedHandler, RestAuthenticationFailureHandler restAuthenticationFailureHandler, AdminRestAuthenticationSuccessHandler restAuthenticationSuccessHandler) {
            this.adminDetailsService = adminDetailsService;
            this.passwordEncoder = passwordEncoder;
            this.restAuthenticationEntryPoint = restAuthenticationEntryPoint;
            this.restAccessDeniedHandler = restAccessDeniedHandler;
            this.restAuthenticationFailureHandler = restAuthenticationFailureHandler;
            this.restAuthenticationSuccessHandler = restAuthenticationSuccessHandler;
        }

        @Override
        public void configure(WebSecurity web) throws Exception {
            web
                    .ignoring()
                    .antMatchers("/", "/index.html", "/components/**", "/fonts/**", "/register/**", "/error/**",
                            "/security/**", "/loading/**", "/login/**", "/error/**");
        }

        @Override
        public void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(adminDetailsService);//.passwordEncoder(passwordEncoder);
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {

            http
                    .antMatcher("/admin/**")
                    .headers().disable()
                    .csrf().disable()
                    .authorizeRequests()
                    .antMatchers("/admin/**", "/views/admin/**").hasAnyAuthority("ADMIN")
                    .anyRequest().authenticated()
                    .and()
                    .exceptionHandling()
                    .authenticationEntryPoint(restAuthenticationEntryPoint)
                    .accessDeniedHandler(restAccessDeniedHandler)
                    .and()
                    .formLogin()
                    .loginProcessingUrl("/admin/login")
                    .successHandler(restAuthenticationSuccessHandler)
                    .failureHandler(restAuthenticationFailureHandler)
                    .usernameParameter("email")
                    .passwordParameter("password")
                    .permitAll()
                    .and()
                    .logout()
                    .logoutUrl("/admin/logout")
                    .logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler())
                    .deleteCookies("JSESSIONID")
                    .permitAll();

        }
    }

    @Configuration
    @Order(2)
    public static class UserSecurityConfiguration extends WebSecurityConfigurerAdapter {

        private UserDetailsService userDetailsService;
        private PasswordEncoder passwordEncoder;
        private RestUnauthorizedEntryPoint restAuthenticationEntryPoint;
        private RestAccessDeniedHandler restAccessDeniedHandler;
        private RestAuthenticationFailureHandler restAuthenticationFailureHandler;
        private UserRestAuthenticationSuccessHandler restAuthenticationSuccessHandler;

        @Autowired
        public UserSecurityConfiguration(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder, RestUnauthorizedEntryPoint restAuthenticationEntryPoint, RestAccessDeniedHandler restAccessDeniedHandler, RestAuthenticationFailureHandler restAuthenticationFailureHandler, UserRestAuthenticationSuccessHandler restAuthenticationSuccessHandler) {
            this.userDetailsService = userDetailsService;
            this.passwordEncoder = passwordEncoder;
            this.restAuthenticationEntryPoint = restAuthenticationEntryPoint;
            this.restAccessDeniedHandler = restAccessDeniedHandler;
            this.restAuthenticationFailureHandler = restAuthenticationFailureHandler;
            this.restAuthenticationSuccessHandler = restAuthenticationSuccessHandler;
        }

        @Override
        public void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(userDetailsService);//.passwordEncoder(passwordEncoder);
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
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

