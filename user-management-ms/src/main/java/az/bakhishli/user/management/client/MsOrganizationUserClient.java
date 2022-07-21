package az.bakhishli.user.management.client;

import az.bakhishli.common.dto.organization.RegisterReqDto;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "${client.organization-user-ms.name}",
        url = "${client.organization-user-ms.host}${client.organization-user-ms.path}")
public interface MsOrganizationUserClient {

    @PostMapping("/api/organizations/v1/register")
    void register(@RequestBody RegisterReqDto reqDto);
}
