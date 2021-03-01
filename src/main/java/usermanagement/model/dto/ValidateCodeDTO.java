package usermanagement.model.dto;

import javax.validation.constraints.*;
import lombok.Data;


@Data
public class ValidateCodeDTO {

    @NotEmpty(message = "Email is not provided")
    @Email(message = "Email is not valid")
    private String email;

    @NotNull(message = "Code is not provided")
    @Size(min = 4, max = 4, message = "Code length should be 4")
    private String code;

}
