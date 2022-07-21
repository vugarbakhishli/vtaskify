package az.bakhishli.common.dto.user;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.validation.constraints.NotNull;
import java.io.Serial;

@Data
public class AuthorityDto implements GrantedAuthority {

    public static final String TABLE_NAME = "authorities";

    @Serial
    private static final long serialVersionUID = -1887785494532505153L;

    @NotNull
    private Long id;

    @NotNull
    private String authority;

    private String title;

}
