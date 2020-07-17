package d2g.vetclinicwebproject.services.services;


import d2g.vetclinicwebproject.services.models.AuthorityServiceModel;
import d2g.vetclinicwebproject.services.models.UserServiceModel;

public interface AuthorityService {
    AuthorityServiceModel createUserRole(UserServiceModel user) ;

    AuthorityServiceModel findById (String id);
}
