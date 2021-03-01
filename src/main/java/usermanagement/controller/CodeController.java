package usermanagement.controller;

import usermanagement.model.dto.*;
import usermanagement.service.CodeService;
import usermanagement.service.UserService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/codes")
public class CodeController {

    private final CodeService codeService;
    private final UserService userService;

    @PostMapping("/generate")
    public void generate(@RequestBody @Valid CodeRequestDTO request) {
        userService.verifyUserByLoginEmail(request.getEmail());
        codeService.generateCode(request.getEmail());
    }

    @PostMapping("/validate")
    public AuthResponseDTO validateCode(@RequestBody @Valid ValidateCodeDTO request) {
        log.info("Validate code request. Data : {}", request);
        String token = codeService.validateCode(request.getCode(), request.getEmail());
        log.info("Validate code request complete. Data : {}", request);

        return new AuthResponseDTO(token);
    }

}
