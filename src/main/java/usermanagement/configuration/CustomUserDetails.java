package usermanagement.configuration;

import java.util.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import usermanagement.model.dto.UserDTO;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomUserDetails implements UserDetails {

    private Long userId;
    private String login;
    private String password;

    public static CustomUserDetails fromUserEntityToCustomUserDetails(UserDTO userEntity) {
        if (Objects.isNull(userEntity)) {
            return null;
        }
        CustomUserDetails userDetails = new CustomUserDetails();
        userDetails.login = userEntity.getEmail();
        return userDetails;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<>();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
