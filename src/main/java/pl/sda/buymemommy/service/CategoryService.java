package pl.sda.buymemommy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sda.buymemommy.model.Category;
import pl.sda.buymemommy.model.MainCategory;
import pl.sda.buymemommy.model.Subcategory;
import pl.sda.buymemommy.repository.CategoryRepository;
import pl.sda.buymemommy.repository.MaincategoryRepository;
import pl.sda.buymemommy.repository.SubcategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {


    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private SubcategoryRepository subcategoryRepository;

    @Autowired
    private MaincategoryRepository maincategoryRepository;

    public List<Category> getAllList() {
        return categoryRepository.findAll();
    }

    public void saveCategory(Category category) {
        categoryRepository.save(category);
    }

    public Category checkAndSaveIfNotExists(Category c) {
        boolean categoryExisting = true;

        Optional<Subcategory> subcategory = subcategoryRepository.findByName(c.getSubcategory().getName());

        Subcategory subcategoryInDatabase;
        if (!subcategory.isPresent()) {
            subcategoryInDatabase = subcategoryRepository.save(c.getSubcategory());
            //
            categoryExisting = false;
        } else {
            subcategoryInDatabase = subcategory.get();
        }

        Optional<MainCategory> mainCategory = maincategoryRepository.findByNameCategory(c.getMainCategory().getNameCategory());
        MainCategory mainCategoryInDatabase;
        if (!mainCategory.isPresent()) {
            mainCategoryInDatabase = maincategoryRepository.save(c.getMainCategory());
            //
            categoryExisting = false;
        } else {
            mainCategoryInDatabase = mainCategory.get();
        }

        if (!categoryExisting) {
            c.setMainCategory(mainCategoryInDatabase);
            c.setSubcategory(subcategoryInDatabase);

            return categoryRepository.save(c);
        }

        Category categoryFromDatabase;
        Optional<Category> categoryInDatabase = categoryRepository.findBySubcategoryAndMainCategory(subcategoryInDatabase, mainCategoryInDatabase);
        if (categoryInDatabase.isPresent()) {
            categoryFromDatabase = categoryInDatabase.get();
        } else {
            c.setMainCategory(mainCategoryInDatabase);
            c.setSubcategory(subcategoryInDatabase);
            categoryFromDatabase = categoryRepository.save(c);
        }

        return categoryFromDatabase;
    }


//
//    public void removeCategory(Long id) {
//        categoryRepository.deleteById(id);
//    }
//
//    public Optional<Category> find(Long id) {
//        return categoryRepository.findById(id);
//    }


}
