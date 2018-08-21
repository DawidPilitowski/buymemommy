package pl.sda.buymemommy.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.sda.buymemommy.model.Category;
import pl.sda.buymemommy.model.MainCategory;
import pl.sda.buymemommy.model.Subcategory;
import pl.sda.buymemommy.repository.CategoryRepository;
import pl.sda.buymemommy.service.CategoryService;
import sun.applet.Main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CategoryComponent {

    private CategoryService categoryService;

    private HashMap<MainCategory, List<Subcategory>> categoryMap = new HashMap<>();

    @Autowired
    public CategoryComponent(CategoryService categoryService) {
        this.categoryService = categoryService;

        createCategory("Gry",
                "Edukacyjne",
                "Planszowe",
                "Komputerowe",
                "Zręcznościowe",
                "Strategiczne",
                "Logiczne",
                "Słowne i liczbowe",
                "Karciane",
                "Quizy",
                "Pozostałe");

        createCategory("Klocki",
                "LEGO",
                "Cobi",
                "Mega blocks",
                "Magnetyczne",
                "Drewniane",
                "Wafle",
                "Pozostałe");

        createCategory("Zabawki edukacyjne",
                "Instrumenty muzyczne",
                "Książeczki",
                "Sortery",
                "Stoliki i stojaki gimnastyczne",
                "Układanki i przeplatanki",
                "Tablety, telefony i komputery",
                "Pozostałe");

        createCategory("Puzzle",
                "Tradycyjne"
                );
    }

    private void createCategory(String mainCategoryName, String... subcategoriesNames) {
        List<Subcategory> subcategories = new ArrayList<>();
        for (String nameOfSub : subcategoriesNames) {
            subcategories.add(new Subcategory(null, nameOfSub));
        }

        MainCategory mainCategory = new MainCategory(null, mainCategoryName);

        for (Subcategory subcategory : subcategories) {
            Category category = new Category(null, mainCategory, subcategory);
            categoryService.checkAndSaveIfNotExists(category);
        }
        categoryMap.put(mainCategory, subcategories);
    }
}
