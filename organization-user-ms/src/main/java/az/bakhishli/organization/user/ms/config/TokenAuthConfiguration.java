package az.bakhishli.organization.user.ms.config;


import az.bakhishli.common.security.auth.services.JwtService;
import az.bakhishli.common.security.auth.services.TokenAuthService;
import az.bakhishli.common.security.config.SecurityProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

@Slf4j
@Configuration
public class TokenAuthConfiguration {

    @Bean
    public TokenAuthService tokenAuthService(JwtService jwtService) {
        return new TokenAuthService(jwtService);
    }

    @Bean
    public JwtService jwtService(@Autowired SecurityProperties securityProperties) {
        log.trace("Security properties {}", securityProperties);
        return new JwtService(Set.of(), Set.of(), securityProperties);
    }

}
