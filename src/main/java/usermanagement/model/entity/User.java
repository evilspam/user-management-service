package usermanagement.model.entity;


import usermanagement.model.enums.MaritalStatus;
import java.time.LocalDate;
import javax.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Entity(name = "user")
public class User {

    @Id
    @SequenceGenerator(name = "user_table_id_seq", sequenceName = "user_table_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "user_table_id_seq")
    private Long id;

    private String email;

    private String name;

    private String surname;

    private LocalDate birthDate;

    @Enumerated(EnumType.STRING)
    private MaritalStatus maritalStatus;

}
