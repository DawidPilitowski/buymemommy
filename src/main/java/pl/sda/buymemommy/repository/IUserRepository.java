package pl.sda.buymemommy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.buymemommy.model.UserModel;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<UserModel, Long> {
    Optional<UserModel> findByUsername(String username);
}
