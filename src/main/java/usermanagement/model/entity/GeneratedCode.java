package usermanagement.model.entity;

import java.time.ZonedDateTime;
import java.util.Random;
import javax.persistence.*;
import lombok.*;


@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Table(name = "generated_code")
public class GeneratedCode {

    @Id
    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "code", nullable = false)
    private String code;

    @Column(name = "confirmed")
    private boolean confirmed;

    @Column(name = "created")
    private ZonedDateTime created;

    //    current constructor used for generation code
    public GeneratedCode(String email) {
        this.email = email;
        this.code = String.valueOf(new Random().nextInt(8999) + 1000);
        this.confirmed = false;
        this.created = ZonedDateTime.now();
    }

}
