package az.bakhishli.organization.user.ms.service.impl;

import az.bakhishli.common.dto.organization.RegisterReqDto;
import az.bakhishli.common.dto.user.UserDto;
import az.bakhishli.common.dto.user.UserIdContainerDto;
import az.bakhishli.organization.user.ms.client.MsUserManagementClient;
import az.bakhishli.organization.user.ms.domain.Organization;
import az.bakhishli.organization.user.ms.exception.OrganizationAlreadyRegisteredException;
import az.bakhishli.organization.user.ms.repository.OrganizationRepository;
import az.bakhishli.organization.user.ms.service.OrganizationService;
import az.bakhishli.organization.user.ms.service.dto.AddUsersToOrganizationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class OrganizationServiceImpl implements OrganizationService {

    private final OrganizationRepository organizationRepository;
    private final MsUserManagementClient msUserManagementClient;

    @Override
    public void register(RegisterReqDto dto) {
        var organization = organizationRepository.findByUserId(dto.getUserId());

        if (organization.isPresent()) {
            throw new OrganizationAlreadyRegisteredException(dto.getUserId());
        }

        organizationRepository.save(Organization
                .builder()
                        .organizationName(dto.getOrganizationName())
                        .address(dto.getAddress())
                        .phoneNumber(dto.getPhoneNumber())
                        .userId(dto.getUserId())
                        .build());
    }

    @Override
    public void addUsers(UserIdContainerDto dto) {
        List<UserDto> users = msUserManagementClient.getAllUsersById(dto);

        Organization organization = new Organization();



    }

}
