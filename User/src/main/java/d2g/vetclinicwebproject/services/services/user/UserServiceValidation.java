package d2g.vetclinicwebproject.services.services.user;

import d2g.vetclinicwebproject.services.models.UserServiceModel;

public interface UserServiceValidation {
    boolean isValidUserRegister(UserServiceModel model);

    boolean isValidUpdateInfo(UserServiceModel model);
}
