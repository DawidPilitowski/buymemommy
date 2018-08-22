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
                "edukacyjne",
                "planszowe",
                "komputerowe",
                "zręcznościowe",
                "strategiczne",
                "logiczne",
                "słowne i liczbowe",
                "karciane",
                "quizy",
                "pozostałe");

        createCategory("Klocki",
                "LEGO",
                "Cobi",
                "Mega blocks",
                "magnetyczne",
                "drewniane",
                "wafle",
                "pozostałe");

        createCategory("Zabawki edukacyjne",
                "instrumenty muzyczne",
                "książeczki",
                "sortery",
                "stoliki i stojaki gimnastyczne",
                "układanki i przeplatanki",
                "tablety, telefony i komputery",
                "pozostałe");

        createCategory("Puzzle",
                "tradycyjne",
                "piankowe",
                "3D/4D",
                "drewniane",
                "pozostałe"
                );
        createCategory("Pojazdy",
                "wózki",
                "pchacze",
                "rowerki",
                "hulajnogi",
                "akumulatorowe",
                "kolejki i akcesoria",
                "autka i akcesoria"
        );
        createCategory("Zabawki do kąpieli", "zabawki do kąpieli");
        createCategory("Pluszaki",
                "tradycyjne",
                "edukacyjne",
                "interaktywne");

        createCategory("Zabawki z bajek i reklam",
                "Auta",
                "Bing",
                "Masza i niedzwiedź",
                "Świnka Peppa",
                "Kraina Lodu",
                "Kubuś Puchatek");

        createCategory("Zabawki ogrodowe",
                "domki",
                "baseny",
                "zjeżdzalnie",
                "trampoliny",
                "piaskownice");

        createCategory("Lalki",
                "bobasy",
                "księżniczki i wróżki",
                "barbie",
                "szmaciane",
                "domki, meble dla lalek",
                "wózki dla lalek");

        createCategory("Niemowlęce",
                "grzechotki",
                "przywieszki",
                "maty i stojaki gimnastyczne",
                "stoliki interaktywne",
                "bujaczki i leżaczki",
                "karuzele i pozytywki");

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
