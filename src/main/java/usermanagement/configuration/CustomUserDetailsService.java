package usermanagement.configuration;


import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import usermanagement.model.dto.UserDTO;
import usermanagement.service.UserService;

@Component
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Override
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDTO user = userService.get(username);
        return CustomUserDetails.fromUserEntityToCustomUserDetails(user);
    }
}
