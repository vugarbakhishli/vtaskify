package az.bakhishli.user.management.service;

import az.bakhishli.common.dto.user.UserDto;
import az.bakhishli.user.management.service.dto.auth.ActivateAccountDto;
import az.bakhishli.user.management.service.dto.auth.SignUpForOrganizationDto;
import az.bakhishli.user.management.service.dto.auth.SignUpForOrganizationUserDto;
import az.bakhishli.common.dto.user.UserIdContainerDto;

import java.util.List;

public interface UserService {
    void signUpForOrganization(SignUpForOrganizationDto signUpDto);
    void signUpForUser(SignUpForOrganizationUserDto signUpDto);
    void activateAccount(ActivateAccountDto dto);
    List<UserDto> getAllUsersById(UserIdContainerDto dto);

}
