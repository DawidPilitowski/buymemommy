package pl.sda.buymemommy.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.sda.buymemommy.model.MainCategory;
import pl.sda.buymemommy.model.Subcategory;
import pl.sda.buymemommy.repository.CategoryRepository;
import pl.sda.buymemommy.service.CategoryService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CategoryComponent {
    @Autowired
    private CategoryService categoryService;
    private HashMap<MainCategory, List<Subcategory>> categoryMap;

    public CategoryComponent() {
        List<Subcategory> subcategories= new ArrayList<>();
        Subcategory sub1 = new Subcategory(null,"Edukacyjne");
        Subcategory sub2 = new Subcategory(null,"Planszowe");
        Subcategory sub3 = new Subcategory(null,"Komputerowe");
        Subcategory sub4 = new Subcategory(null,"Zręcznośćiowe");
        Subcategory sub5 = new Subcategory(null,"Logiczne");
        Subcategory sub6 = new Subcategory(null,"Słowne i liczbowe");
        Subcategory sub7 = new Subcategory(null,"Karciane");

        subcategories.add(sub1);
        subcategories.add(sub2);
        subcategories.add(sub3);
        subcategories.add(sub4);
        subcategories.add(sub5);
        subcategories.add(sub6);
        subcategories.add(sub7);

        MainCategory mainCategory = new MainCategory(null, "Gry");

        categoryMap.put(mainCategory, subcategories);

        for(MainCategory category : categoryMap.keySet()){
            categoryService.saveMainCategory(category);
        }

        List<Subcategory> subcategoriesList = categoryMap.values().stream().flatMap(List::stream).collect(Collectors.toList());
        for(Subcategory category : subcategoriesList){
            categoryService.saveSubCategory(category);
        }
    }
}
