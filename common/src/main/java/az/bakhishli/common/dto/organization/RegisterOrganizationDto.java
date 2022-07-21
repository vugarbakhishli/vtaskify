package az.bakhishli.common.dto.organization;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "password")
public class RegisterOrganizationDto {

    @NotBlank
    private String organizationName;

    @NotNull
    private Long userId;

    @NotBlank
    private String phoneNumber;

    @NotBlank
    private String address;


}
