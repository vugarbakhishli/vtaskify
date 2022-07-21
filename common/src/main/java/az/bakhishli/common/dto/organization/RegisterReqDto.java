package az.bakhishli.common.dto.organization;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterReqDto {

    @NotBlank
    private String organizationName;

    @NotBlank
    private String phoneNumber;

    @NotBlank
    private String address;

    @NotNull
    private Long userId;


}
