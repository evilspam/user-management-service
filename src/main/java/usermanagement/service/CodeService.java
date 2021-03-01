package usermanagement.service;

import usermanagement.exception.CodeExpiredException;
import usermanagement.exception.CodeNotFoundException;
import usermanagement.model.entity.GeneratedCode;
import usermanagement.repository.CodeRepository;
import usermanagement.configuration.jwt.JwtProvider;
import java.time.ZonedDateTime;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class CodeService {

    @Value("${code.expire.time:5}")
    private Long codeExpirationTime;

    private final CodeRepository codeRepository;
    private final MailService emailService;
    private final JwtProvider jwtProvider;

    public void generateCode(String email) {
        GeneratedCode newCode = new GeneratedCode(email);

        log.info("Verification code entity - {} > [{}]", email, newCode.getCode());
        codeRepository.save(newCode);

        log.info("Sending mail with verification code. Data: {}", newCode);
        emailService.sendMail(email, "Verify code", newCode.getCode());
    }

    public String validateCode(String code, String email) {
        log.info("Looking for verification code - {} for email - {}", code, email);
        GeneratedCode codeEntity = codeRepository.findByCodeAndEmail(code, email);
        validateCodeExpiration(email, codeEntity, codeExpirationTime);
        codeEntity.setConfirmed(true);
        codeRepository.save(codeEntity);
        log.info("Code entity set to confirmed. {}", codeEntity);

        return jwtProvider.generateToken(email);
    }

    private void validateCodeExpiration(String code, GeneratedCode codeEntity, Long codeTimeExpiration) {
        if (Objects.isNull(codeEntity)) {
            log.info("Code {} not found", code);
            throw new CodeNotFoundException("code not found");
        }
        if (!codeEntity.getCreated().plusMinutes(codeTimeExpiration).isAfter(ZonedDateTime.now())) {
            log.info("Code for email = {} is expired",  codeEntity.getEmail());
            throw new CodeExpiredException("confirmation code is expired");
        }
    }

}
