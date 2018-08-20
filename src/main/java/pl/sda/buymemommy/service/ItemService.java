package pl.sda.buymemommy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sda.buymemommy.model.Item;
import pl.sda.buymemommy.repository.ItemRepository;

import java.util.List;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;


    public void addProduct(Item item) {
    }


    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }
}
