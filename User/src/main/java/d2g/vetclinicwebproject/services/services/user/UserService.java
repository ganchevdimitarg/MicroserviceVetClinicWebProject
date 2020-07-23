package d2g.vetclinicwebproject.services.services.user;

import d2g.vetclinicwebproject.services.models.CurrentUser;
import d2g.vetclinicwebproject.services.models.UserServiceModel;

public interface UserService {
    UserServiceModel findByUsername(String id);

    void registerUser(UserServiceModel model) throws IllegalAccessException;

    void deleteUser(String username);

    void updateUser(UserServiceModel model);
}
