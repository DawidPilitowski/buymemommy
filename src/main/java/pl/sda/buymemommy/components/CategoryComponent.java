package pl.sda.buymemommy.components;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.sda.buymemommy.model.Category;
import pl.sda.buymemommy.model.MainCategory;
import pl.sda.buymemommy.model.Subcategory;
import pl.sda.buymemommy.service.CategoryService;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class CategoryComponent {

    private CategoryService categoryService;
    @Getter
    private HashMap<MainCategory, List<Subcategory>> categoryMap = new HashMap<>();
    private HashMap<String, Map<String, Category>> categoryMapHashMap = new HashMap<>();

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

        createCategory("Edukacyjne",
                "Instrumenty muzyczne",
                "Książeczki",
                "Sortery",
                "Stoliki i stojaki gimnastyczne",
                "Układanki i przeplatanki",
                "Tablety, telefony i komputery",
                "Pozostałe");

        createCategory("Puzzle",
                "Tradycyjne",
                "Piankowe",
                "3D/4D",
                "Drewniane",
                "Pozostałe"
        );
        createCategory("Pojazdy",
                "Wózki",
                "Pchacze",
                "Rowerki",
                "Hulajnogi",
                "Akumulatorowe",
                "Kolejki i akcesoria",
                "Autka i akcesoria"
        );
        createCategory("Kąpielowe", "Zabawki do kąpieli");
        createCategory("Pluszaki",
                "Tradycyjne",
                "Edukacyjne",
                "Interaktywne");

        createCategory("Z bajek i reklam",
                "Auta",
                "Bing",
                "Masza i niedzwiedź",
                "Świnka Peppa",
                "Kraina Lodu",
                "Kubuś Puchatek");

        createCategory("Ogrodowe",
                "Domki",
                "Baseny",
                "Zjeżdzalnie",
                "Trampoliny",
                "Piaskownice");

        createCategory("Lalki",
                "Bobasy",
                "Księżniczki i wróżki",
                "Barbie",
                "Szmaciane",
                "Domki, meble dla lalek",
                "Wózki dla lalek");

        createCategory("Niemowlęce",
                "Grzechotki",
                "Przywieszki",
                "Maty i stojaki gimnastyczne",
                "Stoliki interaktywne",
                "Bujaczki i leżaczki",
                "Karuzele i pozytywki");
    }

    private void createCategory(String mainCategoryName, String... subcategoriesNames) {
        List<Subcategory> subcategories = new ArrayList<>();
        for (String nameOfSub : subcategoriesNames) {
            subcategories.add(new Subcategory(null, nameOfSub));
        }

        MainCategory mainCategory = new MainCategory(null, mainCategoryName);

        for (Subcategory subcategory : subcategories) {
            Category category = new Category(null, mainCategory, subcategory);
            category = categoryService.checkAndSaveIfNotExists(category);

            addToHashMap(mainCategory, subcategory, category);
        }
        categoryMap.put(mainCategory, subcategories);
    }

    private void addToHashMap(MainCategory mainCategory, Subcategory subcategory, Category category) {
        Map<String, Category> catMap = categoryMapHashMap.get(mainCategory.getNameCategory());
        if (catMap == null) {
            catMap = new HashMap<>();
        }
        catMap.put(subcategory.getName(), category);
        categoryMapHashMap.put(mainCategory.getNameCategory(), catMap);
    }

    public List<Category> betterFind(String main, String sub) {
        if (main.isEmpty()) {
            List<Category> list = categoryMapHashMap.values()
                    .stream()
                    .flatMap(map -> map.values().stream())
                    .collect(Collectors.toList());
            return list;
        }
        if (sub.isEmpty()) {
            return find(main);
        }
        return Arrays.asList(categoryMapHashMap.get(main).get(sub));
    }

    public Category find(String main, String sub) {
        return categoryMapHashMap.get(main).get(sub);
    }

    public List<Category> find(String main) {
        return categoryMapHashMap.get(main).values().stream().collect(Collectors.toList());
    }
}