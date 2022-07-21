package az.bakhishli.organization.user.ms.web.rest;

import az.bakhishli.common.dto.organization.RegisterReqDto;
import az.bakhishli.organization.user.ms.service.OrganizationService;
import az.bakhishli.organization.user.ms.service.dto.AddUsersToOrganizationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/organizations")
public class OrganizationController {

    private final OrganizationService organizationService;

    @PostMapping("/v1/register")
    public ResponseEntity<Void> register(@RequestBody @Validated RegisterReqDto reqDto) {
        organizationService.register(reqDto);
        return ResponseEntity.status(CREATED).build();
    }

    @PostMapping("/v1/showUserId")
    public ResponseEntity<Void> addUsers(@RequestBody AddUsersToOrganizationDto dto) {
        organizationService.addUsers(dto);
        return ResponseEntity.status(CREATED).build();
    }

}
