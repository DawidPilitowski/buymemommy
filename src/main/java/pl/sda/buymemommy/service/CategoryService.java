package pl.sda.buymemommy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sda.buymemommy.model.Category;
import pl.sda.buymemommy.model.MainCategory;
import pl.sda.buymemommy.model.Subcategory;
import pl.sda.buymemommy.repository.CategoryRepository;

import java.util.List;

@Service
public class CategoryService {


    @Autowired
    private CategoryRepository categoryRepository;


    public List<Category> getAllList() {
        return categoryRepository.findAll();
    }

    public void saveCategory(Category category) {
        categoryRepository.save(category);

    }

    public MainCategory saveMainCategory(MainCategory category) {

    }

    public Subcategory saveSubCategory(Subcategory category) {

    }
}
