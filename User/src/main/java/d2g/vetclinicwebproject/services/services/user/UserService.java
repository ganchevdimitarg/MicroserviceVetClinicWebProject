package d2g.vetclinicwebproject.services.services.user;

import d2g.vetclinicwebproject.services.models.CurrentUser;
import d2g.vetclinicwebproject.services.models.UserServiceModel;

public interface UserService {
    UserServiceModel findById(String id);

    UserServiceModel findByUsername(String id);

    UserServiceModel findByUsernameAndPassword(String username, String password);

    void registerUser(UserServiceModel model);

    void deleteUser(String username);

    void updateUser(UserServiceModel model);
}
