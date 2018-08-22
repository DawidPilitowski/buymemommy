package pl.sda.buymemommy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.sda.buymemommy.model.Category;
import pl.sda.buymemommy.model.Item;
import pl.sda.buymemommy.model.dto.ItemDto;
import pl.sda.buymemommy.service.CategoryService;
import pl.sda.buymemommy.service.ItemService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(path = "/item/")
public class ItemController {


    @Autowired
    private ItemService itemService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping(path = "/itemList")
    public String itemList(Model model) {
        List<Item> itemList = itemService.getAllItems();
        model.addAttribute("itemList", itemList);
        return "itemList";
    }

    @GetMapping(path = "/addItem")
    public String add(Model model) {
        Item item = new Item();
        List<Category> categories = categoryService.getAllList();
        model.addAttribute("item", item);
        model.addAttribute("categories", categories);
        return "addItem";
    }

    @PostMapping(path = "/addItem")
    public String add(Item item) {
        itemService.save(item);
        return "redirect:/item/itemList";
    }

    @GetMapping(path = "/remove/{id}")
    public String removeItem(@PathVariable(name = "id") Long id) {
        itemService.removeItem(id);
        return "redirect:/item/itemList";
    }
    @GetMapping(path = "/details/{id}")
    public String detailsOfItem(Model model, @PathVariable(name="id")Long id){
        Optional<Item> itemOptional= itemService.find(id);
        if (itemOptional.isPresent()) {
            Item item= itemOptional.get();
            ItemDto itemDto= new ItemDto(
                    item.getId(),
                    item.getItemName(),
                    item.getDescription(),
                    item.getPrice());
            model.addAttribute("itemDto", itemDto);
        }return "itemDetails";
    }
    @PostMapping(path = "/details")
    public String setItemsDetails(Item item) {
            ItemDto modifiedItem = new ItemDto(
                    item.getId(),
                    item.getItemName(),
                    item.getDescription(),
                    item.getPrice());

       itemService.save(item);
       return "redirect:/item/itemList";
    }

}