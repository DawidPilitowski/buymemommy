package pl.sda.buymemommy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sda.buymemommy.model.Item;
import pl.sda.buymemommy.repository.ItemRepository;

import java.util.List;
import java.util.Optional;

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


}


