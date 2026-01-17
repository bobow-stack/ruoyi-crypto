package com.ruoyi.app.config;

import com.ruoyi.app.security.filter.AppJwtAuthenticationTokenFilter;
import com.ruoyi.app.security.handle.AppLogoutSuccessHandler;
import com.ruoyi.framework.config.properties.PermitAllUrlProperties;
import com.ruoyi.framework.security.handle.AuthenticationEntryPointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.web.filter.CorsFilter;

/**
 * APP 端独立 Security 配置
 */
@Configuration
public class AppSecurityConfig
{
    @Autowired
    private AuthenticationEntryPointImpl unauthorizedHandler;

    @Autowired
    private AppLogoutSuccessHandler logoutSuccessHandler;

    @Autowired
    private AppJwtAuthenticationTokenFilter authenticationTokenFilter;

    @Autowired
    private PermitAllUrlProperties permitAllUrl;

    @Autowired
    private CorsFilter corsFilter;

    @Bean
    @Order(0)
    public SecurityFilterChain appFilterChain(HttpSecurity httpSecurity) throws Exception
    {
        return httpSecurity
                .antMatcher("/app/**")
                .csrf(csrf -> csrf.disable())
                .headers(headers -> headers.cacheControl(cache -> cache.disable()).frameOptions(options -> options.sameOrigin()))
                .exceptionHandling(exception -> exception.authenticationEntryPoint(unauthorizedHandler))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(requests -> {
                    permitAllUrl.getUrls().forEach(url -> requests.antMatchers(url).permitAll());
                    requests.antMatchers("/app/auth/login", "/app/auth/register").permitAll()
                            .antMatchers(HttpMethod.GET, "/", "/*.html", "/**/*.html", "/**/*.css", "/**/*.js").permitAll()
                            .anyRequest().authenticated();
                })
                .logout(logout -> logout.logoutUrl("/app/auth/logout").logoutSuccessHandler(logoutSuccessHandler))
                .addFilterBefore(authenticationTokenFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(corsFilter, AppJwtAuthenticationTokenFilter.class)
                .addFilterBefore(corsFilter, LogoutFilter.class)
                .build();
    }
}
