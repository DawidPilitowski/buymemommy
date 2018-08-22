package pl.sda.buymemommy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sda.buymemommy.model.Item;
import pl.sda.buymemommy.model.dto.ItemDto;
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
            items.addAll(itemRepository.findAllByItemName(word));
        }
        List<Item> itemList = new ArrayList<>();
        itemList.addAll(items);
        return itemList;
    }

//    public List<Item> searchByNameLike(String phrase) {
//        String nameToLowerCase = phrase.toLowerCase();
//        String words[] = nameToLowerCase.split("\\s+");
//        Set<Item> items = new HashSet<>();
//        for (String word : words) {
//            items.addAll(itemRepository.findByItemNameLike(word));
//        }
//        List<Item> itemList = new ArrayList<>();
//        itemList.addAll(items);
//        return itemList;
//    }



}


