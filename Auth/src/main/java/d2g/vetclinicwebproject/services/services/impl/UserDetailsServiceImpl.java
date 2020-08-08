package d2g.vetclinicwebproject.services.services.impl;

import d2g.vetclinicwebproject.data.model.AuthorityEntity;
import d2g.vetclinicwebproject.data.model.UserEntity;
import d2g.vetclinicwebproject.data.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        Optional<UserEntity> userOpt = userRepository.findByUsername(username);

        LOGGER.debug("Trying to load user {}. Successful? {}", username, userOpt.isPresent());

        return userOpt.
                map(this::map).
                orElseThrow(() -> new UsernameNotFoundException("User " + username + " not found!"));
    }

    private User map(UserEntity user) {
        List<GrantedAuthority> authorities = user.
                getAuthorities().
                stream().
                map(r -> new SimpleGrantedAuthority(r.getRole())).
                collect(Collectors.toList());

        User result = new User(
                user.getUsername(),
                user.getPassword() != null ? user.getPassword() : "",
                authorities);

        if (user.getPassword() == null) {
            result.eraseCredentials();
        }

        return result;
    }
}
