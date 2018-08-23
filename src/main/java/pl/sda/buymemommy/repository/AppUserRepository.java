package pl.sda.buymemommy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.buymemommy.model.AppUser;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    Optional<AppUser> findByUsername(String username);
}
