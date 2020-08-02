package d2g.vetclinicwebproject.services.services;

import d2g.vetclinicwebproject.data.model.UserEntity;
import d2g.vetclinicwebproject.services.models.UserServiceModel;

public interface UserEntityService {
    UserServiceModel findByUsername(String username);

    void register(UserServiceModel user);

    UserEntity getOrCreateUser(String email);
}
