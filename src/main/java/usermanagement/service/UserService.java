package usermanagement.service;

import usermanagement.exception.*;
import usermanagement.mapper.UserMapper;
import usermanagement.repository.UserRepository;
import usermanagement.model.dto.UserDTO;
import usermanagement.model.entity.User;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper mapper;

    public UserDTO create(UserDTO permissionDto) {
        userRepository.findByEmail(permissionDto.getName())
                .ifPresent(role -> {
                    throw new EntityAlreadyExistException("User email should be unique!");
                });
        User permission = mapper.toEntity(permissionDto);
        return mapper.toDTO(userRepository.save(permission));
    }

    public UserDTO update(Long id, UserDTO dto) {
        User permission = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User with such id is not present!"));
        userRepository.findByEmail(dto.getEmail())
                .ifPresent(r -> {
                    throw new EntityAlreadyExistException("User email should be unique!");
                });
        Optional.ofNullable(dto.getName())
                .ifPresent(permission::setName);
        Optional.ofNullable(dto.getEmail())
                .ifPresent(permission::setEmail);
        Optional.ofNullable(dto.getMaritalStatus())
                .ifPresent(permission::setMaritalStatus);
        Optional.ofNullable(dto.getSurname())
                .ifPresent(permission::setSurname);
        Optional.ofNullable(dto.getBirthDate())
                .ifPresent(permission::setBirthDate);
        return mapper.toDTO(userRepository.save(permission));
    }

    @Transactional(readOnly = true)
    public UserDTO get(Long id) {
        return userRepository.findById(id).map(mapper::toDTO)
                .orElseThrow(() -> new EntityNotFoundException("User with such id is missing!"));
    }

    @Transactional(readOnly = true)
    public List<UserDTO> getAll() {
        return userRepository.findAll().stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public UserDTO get(String email) {
        return userRepository.findByEmail(email).map(mapper::toDTO).orElse(null);
    }

    public void delete(Long id) {
        userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User with such id is not present!"));
        userRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public void verifyUserByLoginEmail(String login) {
        Optional<User> user = userRepository.findByEmail(login);
        if (user.isEmpty()) {
            throw new InvalidEmailException("Invalid email");
        }
    }

}
