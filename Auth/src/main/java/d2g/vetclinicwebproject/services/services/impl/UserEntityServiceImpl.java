package d2g.vetclinicwebproject.services.services.impl;

import d2g.vetclinicwebproject.data.model.AuthorityEntity;
import d2g.vetclinicwebproject.data.model.UserEntity;
import d2g.vetclinicwebproject.data.repository.UserRepository;
import d2g.vetclinicwebproject.services.models.GuestServiceModel;
import d2g.vetclinicwebproject.services.models.UserServiceModel;
import d2g.vetclinicwebproject.services.services.AuthorityService;
import d2g.vetclinicwebproject.services.services.GuestService;
import d2g.vetclinicwebproject.services.services.UserEntityService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.hibernate.bytecode.BytecodeLogger.LOGGER;

@Service
@Getter
@Setter
@AllArgsConstructor
public class UserEntityServiceImpl implements UserEntityService {
    private static final Logger logger = LoggerFactory.getLogger(UserEntityServiceImpl.class);

    private final UserRepository userRepository;
    private final AuthorityService authorityService;
    private final GuestService guestService;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;


    @Override
    public UserServiceModel findByUsername(String username) {
        return modelMapper.map(userRepository.findByUsername(username), UserServiceModel.class);
    }

    @Override
    public void register(UserServiceModel model) {
        UserEntity user = new UserEntity();
        user.setUsername(model.getUsername());
        user.setPassword(passwordEncoder.encode(model.getPassword()));
        user.setEnabled(true);

        AuthorityEntity adminAdminAuthorityEntity = new AuthorityEntity();
        adminAdminAuthorityEntity.setRole("ROLE_USER");
        adminAdminAuthorityEntity.setUser(user);

        user.setAuthorities(List.of(adminAdminAuthorityEntity));

        userRepository.saveAndFlush(user);

        registerGuestUser(user);
    }

    @Override
    public UserEntity getOrCreateUser(String email) {
        Objects.requireNonNull(email);

        Optional<UserEntity> userEntityOptional = userRepository.findByUsername(email);

        return userEntityOptional.orElseGet(() -> createUser(email));
    }

    private UserEntity createUser(String email) {
        LOGGER.info("Creating a new user with email [GDPR]");
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(email);

        AuthorityEntity userRole = new AuthorityEntity();
        userRole.setRole("ROLE_USER");
        userRole.setUser(userEntity);
        userEntity.setEnabled(true);

        userEntity.setAuthorities(List.of(userRole));

        registerGuestUser(userEntity);

        userRepository.saveAndFlush(userEntity);

        return userEntity;
    }

    private void registerGuestUser(UserEntity user) {
        guestService.registerUser(modelMapper.map(user, GuestServiceModel.class));
    }
}
