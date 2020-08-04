package in.keepgrowing.jwtspringbootangularscaffolding.security;

import in.keepgrowing.jwtspringbootangularscaffolding.user.UserService;
import in.keepgrowing.jwtspringbootangularscaffolding.user.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private UserService userService;

    public CustomUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        return userService.findByUsername(username)
                .map(this::getUserDetails)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("Username: %s not found", username)));
    }

    private org.springframework.security.core.userdetails.User getUserDetails(User u) {
        return new org.springframework.security.core.userdetails.User(
                u.getUserCredentials().getUsername(),
                u.getUserCredentials().getPassword(),
                getGrantedAuthorities(u));
    }

    private List<GrantedAuthority> getGrantedAuthorities(User u) {
        return AuthorityUtils
                .commaSeparatedStringToAuthorityList(u.getUserCredentials().getRole());
    }
}
