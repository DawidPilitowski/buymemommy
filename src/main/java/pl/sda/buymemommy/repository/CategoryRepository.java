package pl.sda.buymemommy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.sda.buymemommy.model.Category;
import pl.sda.buymemommy.model.MainCategory;
import pl.sda.buymemommy.model.Subcategory;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findBySubcategoryAndMainCategory(Subcategory subcategory, MainCategory mainCategory);
}
