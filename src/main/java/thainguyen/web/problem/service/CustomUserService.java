package thainguyen.web.problem.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import thainguyen.web.problem.dto.UserDto;
import thainguyen.web.problem.model.ApplicationUser;
import thainguyen.web.problem.model.CustomUser;
import thainguyen.web.problem.repository.UserRepository;

import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class CustomUserService implements UserDetailsService, UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ApplicationUser user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("user " + username + " not found");
        }
        return new CustomUser(user.getUsername()
                , user.getPassword()
                , true, true
                , true, true,
                Arrays.asList(new SimpleGrantedAuthority("ROLE_" + user.getRole().name()))
        );
    }

    @Override
    public ApplicationUser createUser(UserDto userDto) {
        ApplicationUser applicationUser = new ApplicationUser();
        applicationUser.setFirstName(userDto.getFirstName());
        applicationUser.setLastName(userDto.getLastName());
        applicationUser.setEmail(userDto.getEmail());
        applicationUser.setUsername(userDto.getUsername());
        applicationUser.setPassword(encoder.encode(userDto.getPassword()));
        applicationUser.setRole(ApplicationUser.Role.USER);
        return userRepository.save(applicationUser);
    }
}
