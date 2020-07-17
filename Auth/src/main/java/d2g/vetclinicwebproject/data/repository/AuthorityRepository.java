package d2g.vetclinicwebproject.data.repository;

import d2g.vetclinicwebproject.data.model.AuthorityEntity;
import d2g.vetclinicwebproject.data.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface AuthorityRepository extends JpaRepository<AuthorityEntity, String> {

    @Transactional
    @Modifying
    void deleteAuthorityEntityByUser(UserEntity user);
}
