package az.bakhishli.user.management.web.rest.users;

import az.bakhishli.common.dto.user.UserDto;
import az.bakhishli.user.management.service.UserService;
import az.bakhishli.common.dto.user.UserIdContainerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @PostMapping("/getAllUsersById")
    public List<UserDto> getAllUsersById(@RequestBody UserIdContainerDto dto) {
        return userService.getAllUsersById(dto);
    }

}
