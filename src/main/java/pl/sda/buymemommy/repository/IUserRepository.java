package pl.sda.buymemommy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import pl.sda.buymemommy.model.UserModel;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<UserModel, Long> {
    Optional<UserModel> findByUsername(String username);

    UserModel findOneByUsername(String name);


    @Modifying
    @Transactional
    @Query("update UserModel u set u.email = :email, u.address = :address, "
            + "u.surname = :surname "
            + "where u.username = :username")
    int updateUser(
            @Param("username") String username,
            @Param("email") String email,
            @Param("address") String address,
            @Param("surname") String surname);
}
