package pl.sda.buymemommy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.buymemommy.model.Subcategory;

import java.util.Optional;

public interface SubcategoryRepository extends JpaRepository<Subcategory, Long> {
    Optional<Subcategory> findByName(String name);
}
