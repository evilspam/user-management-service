package usermanagement.model.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CodeGenerationDTO {

    @NotEmpty(message = "Email is not provided")
    @Email(message = "Email is not valid")
    private String email;

}
