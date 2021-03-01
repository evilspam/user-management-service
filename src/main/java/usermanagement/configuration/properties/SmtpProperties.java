package usermanagement.configuration.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "smtp")
public class SmtpProperties {

    private String host;
    private Integer port;
    private String username;
    private String password;
    private Boolean debug;
    private String from;
}
