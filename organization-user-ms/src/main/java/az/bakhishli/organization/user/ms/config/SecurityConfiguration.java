package az.bakhishli.organization.user.ms.config;

import az.bakhishli.common.security.auth.AuthenticationEntryPointConfigurer;
import az.bakhishli.common.security.auth.services.JwtService;
import az.bakhishli.common.security.auth.services.TokenAuthService;
import az.bakhishli.common.security.config.BaseSecurityConfig;
import az.bakhishli.common.security.config.SecurityProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import java.util.List;

@Slf4j
@Import({SecurityProperties.class, JwtService.class,
        AuthenticationEntryPointConfigurer.class})
@EnableWebSecurity
public class SecurityConfiguration extends BaseSecurityConfig {

    public SecurityConfiguration(SecurityProperties securityProperties, JwtService jwtService) {
        super(securityProperties, List.of(new TokenAuthService(jwtService)));
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(HttpMethod.GET, "/api/organizations/**")
                .permitAll()
                .antMatchers(HttpMethod.POST, "/api/organizations/**")
                .permitAll();
        super.configure(http);
    }
}
