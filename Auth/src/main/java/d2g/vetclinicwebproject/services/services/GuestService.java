package d2g.vetclinicwebproject.services.services;


import d2g.vetclinicwebproject.services.models.GuestServiceModel;

public interface GuestService {
    GuestServiceModel getUserHome(String id);

    void registerUser(GuestServiceModel user);

    void update(GuestServiceModel user);

    void deleteUser(String username);
}
