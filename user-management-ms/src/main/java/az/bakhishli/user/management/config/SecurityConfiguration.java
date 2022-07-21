package az.bakhishli.user.management.config;

import az.bakhishli.common.security.auth.services.JwtService;
import az.bakhishli.common.security.auth.services.TokenAuthService;
import az.bakhishli.common.security.config.BaseSecurityConfig;
import az.bakhishli.common.security.config.SecurityProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Slf4j
@EnableWebSecurity
@ComponentScan("az.bakhishli.common.security")
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfiguration extends BaseSecurityConfig {

    public SecurityConfiguration(SecurityProperties securityProperties, JwtService jwtService) {
        super(securityProperties, List.of(new TokenAuthService(jwtService)));
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/auth/sign-in/").permitAll()
                .antMatchers("/auth/**").permitAll()
                .antMatchers("/users/**").permitAll()
                .antMatchers("/auth/sign-up/**")
                .permitAll();
        super.configure(http);
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
