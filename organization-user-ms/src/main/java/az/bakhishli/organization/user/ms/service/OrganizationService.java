package az.bakhishli.organization.user.ms.service;

import az.bakhishli.common.dto.organization.RegisterReqDto;
import az.bakhishli.common.dto.user.UserIdContainerDto;
import az.bakhishli.organization.user.ms.service.dto.AddUsersToOrganizationDto;

import java.util.Set;

public interface OrganizationService {
    void register(RegisterReqDto dto);
    void addUsers(UserIdContainerDto dto);
}
