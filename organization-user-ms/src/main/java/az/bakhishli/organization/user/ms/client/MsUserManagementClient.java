package az.bakhishli.organization.user.ms.client;

import az.bakhishli.common.dto.user.UserDto;
import az.bakhishli.common.dto.user.UserIdContainerDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "${client.user-management-ms.name}",
        url = "${client.user-management-ms.host}${client.user-management-ms.path}")
public interface MsUserManagementClient {

    @PostMapping("/users/getAllUsersById")
    List<UserDto> getAllUsersById(@RequestBody UserIdContainerDto dto);

}
