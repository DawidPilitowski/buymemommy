package pl.sda.buymemommy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.buymemommy.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
