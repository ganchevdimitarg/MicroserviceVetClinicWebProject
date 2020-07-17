package d2g.vetclinicwebproject;

import d2g.vetclinicwebproject.data.model.AuthorityEntity;
import d2g.vetclinicwebproject.data.model.UserEntity;
import d2g.vetclinicwebproject.data.repository.AuthorityRepository;
import d2g.vetclinicwebproject.data.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class AdminInit implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {

        if (userRepository.findByUsername("strange").isEmpty()) {
            createUserDoctor();
        }
    }

    private void createUserDoctor() {
        UserEntity admin = new UserEntity();
        admin.setUsername("strange");
        admin.setPassword(passwordEncoder.encode("!1Qazx"));
        admin.setEnabled(true);

        AuthorityEntity adminAdminAuthorityEntity = new AuthorityEntity();
        adminAdminAuthorityEntity.setName("ROLE_ADMIN");
        adminAdminAuthorityEntity.setUser(admin);

        admin.setAuthorities(List.of(adminAdminAuthorityEntity));

        userRepository.saveAndFlush(admin);
    }
}
