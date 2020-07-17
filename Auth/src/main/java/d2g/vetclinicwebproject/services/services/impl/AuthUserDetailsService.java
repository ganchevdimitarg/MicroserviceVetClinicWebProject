package d2g.vetclinicwebproject.services.services.impl;

import d2g.vetclinicwebproject.data.model.AuthorityEntity;
import d2g.vetclinicwebproject.data.model.UserEntity;
import d2g.vetclinicwebproject.data.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AuthUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        Optional<UserEntity> userEntityOpt = userRepository.findByUsername(username);
        return userEntityOpt.
                map(this::map).
                orElseThrow(() -> new UsernameNotFoundException("User " + username + " not found!"));
    }

    private UserDetails map(UserEntity userEntity) {
        return new User(
                userEntity.getUsername(),
                userEntity.getPassword(),
                userEntity.
                        getAuthorities().
                        stream().
                        map(this::map).
                        collect(Collectors.toList())
        );
    }

    private GrantedAuthority map(AuthorityEntity authorityEntity) {
        return new SimpleGrantedAuthority(authorityEntity.getName());
    }
}
