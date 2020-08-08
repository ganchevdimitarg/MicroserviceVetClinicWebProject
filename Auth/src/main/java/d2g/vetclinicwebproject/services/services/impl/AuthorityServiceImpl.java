package d2g.vetclinicwebproject.services.services.impl;

import d2g.vetclinicwebproject.data.model.AuthorityEntity;
import d2g.vetclinicwebproject.data.model.UserEntity;
import d2g.vetclinicwebproject.data.repository.AuthorityRepository;
import d2g.vetclinicwebproject.services.models.AuthorityServiceModel;
import d2g.vetclinicwebproject.services.models.UserServiceModel;
import d2g.vetclinicwebproject.services.services.AuthorityService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthorityServiceImpl implements AuthorityService {

    private final AuthorityRepository authorityRepository;
    private final ModelMapper modelMapper;

    @Override
    public AuthorityServiceModel createUserRole(UserServiceModel user) {
        AuthorityEntity authorityEntity = new AuthorityEntity();
        authorityEntity.setRole("ROLE_USER");
        authorityEntity.setUser(modelMapper.map(user, UserEntity.class));
        authorityRepository.saveAndFlush(authorityEntity);

        return modelMapper.map(authorityEntity, AuthorityServiceModel.class);
    }

    @Override
    public AuthorityServiceModel findById(String id) {
        return modelMapper.map(authorityRepository.findById(id).orElse(null), AuthorityServiceModel.class);
    }

}
