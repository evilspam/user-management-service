package usermanagement.model.dto;

import usermanagement.model.enums.MaritalStatus;
import java.time.LocalDate;
import javax.validation.constraints.NotNull;
import lombok.*;


@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private Long id;

    @NotNull(message = "Email is not provided")
    private String email;

    private String name;

    private String surname;

    private LocalDate birthDate;

    private MaritalStatus maritalStatus;

}
