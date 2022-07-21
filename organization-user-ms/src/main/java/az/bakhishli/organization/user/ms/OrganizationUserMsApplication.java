package az.bakhishli.organization.user.ms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class OrganizationUserMsApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrganizationUserMsApplication.class, args);
    }
}
