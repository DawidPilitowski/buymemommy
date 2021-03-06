package pl.sda.buymemommy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sda.buymemommy.model.Category;
import pl.sda.buymemommy.model.Gender;
import pl.sda.buymemommy.model.Item;
import pl.sda.buymemommy.repository.ItemRepository;

import java.util.*;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;


    @Autowired
    private CategoryService categoryService;

    public void addProduct(Item item) {
    }

    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    public Item save(Item item) {
        return itemRepository.save(item);
    }

    public void removeItem(Long id) {
        itemRepository.deleteById(id);
    }

    public Optional<Item> find(Long id) {
        return itemRepository.findById(id);
    }


    public List<Item> searchByName(String name) {
        String nameToLowerCase = name.toLowerCase();
        String words[] = nameToLowerCase.split("\\s+");
        Set<Item> items = new HashSet<>();
        for (String word : words) {
            items.addAll(itemRepository.findAllByItemNameContains(word));
        }
        return new ArrayList<>(items);
    }


    public List<Item> findByCategory(Category searchCategory) {
        return itemRepository.findAllByCategoryList(Arrays.asList(searchCategory));
    }

    public List<Item> findByCategory(List<Category> searchCategory) {
        List<Item> joinedItems = new ArrayList<>();

        for (Category cat : searchCategory) {
            joinedItems.addAll(itemRepository.findAllByCategoryList(Arrays.asList(cat)));
        }

        return joinedItems;
    }

    public List<Item> searchByAge(int age) {
        return itemRepository.findAllByAgeFromGreaterThan(age);
    }

    public List<Item> searchBy(List<Category> categories, String phrase, int ageFrom, Gender gender) {
        List<Item> joinedItems = new ArrayList<>();
        for (Category c : categories) {
            if(gender ==null) {
                joinedItems.addAll(itemRepository.findAllByAgeFromGreaterThanAndCategoryListAndItemNameContains(
                        ageFrom, Arrays.asList(c), phrase));
            }else{
                joinedItems.addAll(itemRepository.findAllByAgeFromGreaterThanAndCategoryListAndItemNameContainsAndGender(
                        ageFrom, Arrays.asList(c), phrase, gender));
            }
        }
        return joinedItems;
    }
}


