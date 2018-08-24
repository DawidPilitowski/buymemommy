package pl.sda.buymemommy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.sda.buymemommy.components.CategoryComponent;
import pl.sda.buymemommy.model.Category;
import pl.sda.buymemommy.model.Item;
import pl.sda.buymemommy.model.dto.ItemDTO;
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

    @Autowired
    private CategoryComponent categoryComponent;

    @GetMapping(path = "/itemList")
    public String itemList(Model model) {
        List<Item> itemList = itemService.getAllItems();
        model.addAttribute("itemList", itemList);
//        model.addAttribute("category", itemList);
        return "oldItemList";
    }

    @GetMapping(path = "/test/{main}/{sub}")
    public String test(Model model, @PathVariable(name = "main") String main, @PathVariable(name = "sub", required = false) String sub) {
        List<Item> itemList;
        Category searchCategory = categoryComponent.find(main, sub);
        itemList = itemService.findByCategory(searchCategory);
        model.addAttribute("itemList", itemList);
        return "oldItemList";
    }

    @GetMapping(path = "/test/{main}")
    public String test(Model model, @PathVariable(name = "main") String main) {
        List<Item> itemList;
        itemList = itemService.findByCategory(categoryComponent.find(main));
        model.addAttribute("itemList", itemList);
        return "oldItemList";
    }

    @GetMapping(path = "/itemList/{phrase}")
    public String itemList(Model model, @PathVariable(name = "phrase") String phrase) {
        List<Item> itemList= itemService.searchByName(phrase);
        model.addAttribute("itemList", itemList);
        return "oldItemList";
    }

    @GetMapping(path = "/addItem")
    public String add(Model model) {
        Item item = new Item();
        List<Category> categories = categoryService.getAllList();
        model.addAttribute("item", item);
        model.addAttribute("categories", categories);
        return "oldAddItem";
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

    @GetMapping(path = "/edit/{id}")
    public String editOfItem(Model model, @PathVariable(name = "id") Long id) {
        Optional<Item> itemOptional = itemService.find(id);
        if (itemOptional.isPresent()) {
            Item item = itemOptional.get();
            ItemDTO itemDto = new ItemDTO(
                    item.getId(),
                    item.getItemName(),
                    item.getDescription(),
                    item.getPrice());
            model.addAttribute("itemDto", itemDto);

        }
        return "oldItemEdit";
    }

    @PostMapping(path = "/edit")
    public String setItemsEdit(ItemDTO item) {
        Optional<Item> editedItem = itemService.find(item.getId());
        if(editedItem.isPresent()){
            Item edited = editedItem.get();
            edited.setItemName(item.getItemName());
            edited.setDescription(item.getDescription());
            edited.setPrice(item.getPrice());

            itemService.save(edited);
        }

        return "redirect:/item/itemList";
    }
    @GetMapping(path = "/details/{id}")
    public String itemDetails(Model model, @PathVariable(name = "id") Long id) {
       Optional<Item> itemOptional= itemService.find(id);

      if(itemOptional.isPresent()){
          Item item = itemOptional.get();
          model.addAttribute("item", item);
      }else {
          return "redirect:/item/itemList";
      }
        return "itemDetails";
    }
}