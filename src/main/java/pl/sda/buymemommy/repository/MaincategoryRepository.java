package pl.sda.buymemommy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.buymemommy.model.MainCategory;
import sun.applet.Main;

import java.util.Optional;

public interface MaincategoryRepository extends JpaRepository<MainCategory, Long> {
    Optional<MainCategory> findByNameCategory(String name);
}
