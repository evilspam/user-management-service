package usermanagement.model.dto;

import javax.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CodeRequestDTO {

    @NotNull(message = "Email is not provided")
    private String email;
}
