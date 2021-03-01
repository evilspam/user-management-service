package usermanagement.mapper;

import usermanagement.model.dto.UserDTO;
import usermanagement.model.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User toEntity(UserDTO dto) {
        return User.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .surname(dto.getSurname())
                .maritalStatus(dto.getMaritalStatus())
                .birthDate(dto.getBirthDate())
                .build();
    }

    public UserDTO toDTO(User entity) {
        return UserDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .email(entity.getEmail())
                .surname(entity.getSurname())
                .maritalStatus(entity.getMaritalStatus())
                .birthDate(entity.getBirthDate())
                .build();
    }

}
