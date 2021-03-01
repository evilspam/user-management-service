package usermanagement.configuration;

import usermanagement.configuration.properties.SmtpProperties;
import java.util.Properties;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
@RequiredArgsConstructor
public class EmailConfiguration {

    private final SmtpProperties smtpProperties;

    @Bean
    public JavaMailSender mailSender() {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setProtocol("smtps");
        javaMailSender.setHost(smtpProperties.getHost());
        javaMailSender.setPort(smtpProperties.getPort());
        javaMailSender.setUsername(smtpProperties.getUsername());
        javaMailSender.setPassword(smtpProperties.getPassword());

        Properties properties = javaMailSender.getJavaMailProperties();
        properties.put("mail.transport.protocol", "smtp");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.debug", smtpProperties.getDebug().toString());
        properties.put("mail.smtps.ssl.checkserveridentity", "true");
        properties.put("mail.smtps.ssl.trust", "*");
        return javaMailSender;
    }
}
