package usermanagement.advice;


import usermanagement.exception.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@Slf4j
@ControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> entityNotFoundExceptionHandler(EntityNotFoundException e) {
        log.error("Exception has occurred during program execution: '{}'", e.getMessage());
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EntityAlreadyExistException.class)
    public ResponseEntity<String> entityAlreadyExistExceptionHandler(EntityAlreadyExistException e) {
        log.error("Exception has occurred during program execution: '{}'", e.getMessage());
        return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(CodeExpiredException.class)
    public ResponseEntity<String> codeExpiredExceptionHandler(CodeExpiredException e) {
        log.error("Exception has occurred during program execution: '{}'", e.getMessage());
        return new ResponseEntity<>(e.getMessage(), HttpStatus.GONE);
    }

    @ExceptionHandler(CodeNotFoundException.class)
    public ResponseEntity<String> codeNotFoundExceptionHandler(CodeNotFoundException e) {
        log.error("Exception has occurred during program execution: '{}'", e.getMessage());
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidEmailException.class)
    public ResponseEntity<String> invalidLoginExceptionHandler(InvalidEmailException e) {
        log.error("Exception has occurred during program execution: '{}'", e.getMessage());
        return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
    }

}

