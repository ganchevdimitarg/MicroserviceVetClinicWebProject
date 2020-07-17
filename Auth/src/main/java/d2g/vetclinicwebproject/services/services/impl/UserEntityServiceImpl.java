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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Getter
@Setter
@AllArgsConstructor
public class UserEntityServiceImpl implements UserEntityService {
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
        adminAdminAuthorityEntity.setName("ROLE_USER");
        adminAdminAuthorityEntity.setUser(user);

        user.setAuthorities(List.of(adminAdminAuthorityEntity));

        userRepository.saveAndFlush(user);

        guestService.registerUser(modelMapper.map(user, GuestServiceModel.class));
    }

}
