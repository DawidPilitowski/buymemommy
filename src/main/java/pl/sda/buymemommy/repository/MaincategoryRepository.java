package pl.sda.buymemommy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.sda.buymemommy.model.MainCategory;

import java.util.Optional;

@Repository
public interface MaincategoryRepository extends JpaRepository<MainCategory, Long> {
    Optional<MainCategory> findByNameCategory(String name);
}
