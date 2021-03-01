package usermanagement.controller;

import static org.springframework.http.HttpStatus.NO_CONTENT;

import usermanagement.model.dto.UserDTO;
import usermanagement.service.UserService;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public UserDTO createUser(@RequestBody @Valid UserDTO dto) {
        log.info("Request for create user: {}", dto);
        return userService.create(dto);
    }

    @PatchMapping("/{userId}")
    public UserDTO updateUser(@PathVariable Long userId, @RequestBody UserDTO dto) {
        log.info("Request for update user: {}", dto);
        return userService.update(userId, dto);
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(NO_CONTENT)
    public void deleteUser(@PathVariable Long userId) {
        log.info("Request to delete user with id: {}", userId);
        userService.delete(userId);
    }

    @GetMapping("/{userId}")
    public UserDTO getUser(@PathVariable Long userId) {
        log.info("Request to get user with id: {}", userId);
        return userService.get(userId);
    }

    @GetMapping
    public List<UserDTO> getUsers() {
        log.info("Request to get all users");
        return userService.getAll();
    }

}
