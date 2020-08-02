package d2g.vetclinicwebproject.data.repositories;

import d2g.vetclinicwebproject.data.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findById(String id);

    Optional<User> findByUsername(String username);

    @Transactional
    @Modifying
    @Query("update User u set u.name= :name, u.address= :address, u.phoneNumber= :phoneNumber, u.imageUrl= :imageUrl  where u.username= :username")
    void update(@Param("name") String name, @Param("address") String address, @Param("phoneNumber") String phoneNumber, @Param("imageUrl") String imageUrl, @Param("username") String username);

    @Transactional
    @Modifying
    @Query("delete from User u where u.username= :username")
    void deleteByUsername(@Param("username") String username);
}
