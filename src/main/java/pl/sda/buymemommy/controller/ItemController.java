package pl.sda.buymemommy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.sda.buymemommy.components.CategoryComponent;
import pl.sda.buymemommy.model.Category;
import pl.sda.buymemommy.model.Item;
import pl.sda.buymemommy.model.dto.ItemDTO;
import pl.sda.buymemommy.service.CategoryService;
import pl.sda.buymemommy.service.ItemService;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        model.addAttribute("images", itemList.stream().map(item -> {
            if (item.getImage().length == 0) {
                return "";
            } else {
                return new String(Base64.getEncoder().encode(item.getImage()));
            }
        }).collect(Collectors.toList()));
        return "itemList";
    }

    @GetMapping(path = "/itemListSearch")
    public String itemList(Model model,
                           @RequestParam(name = "main", defaultValue = "") String main,
                           @RequestParam(name = "sub", defaultValue = "") String sub,
                           @RequestParam(name = "phrase", defaultValue = "") String phrase,
                           @RequestParam(name = "ageFrom", defaultValue = "0") int ageFrom) {
        List<Category> searchCategory = categoryComponent.betterFind(main, sub);

        List<Item> itemList = itemService.searchBy(searchCategory, phrase, ageFrom);

        model.addAttribute("itemList", itemList);
        model.addAttribute("images", returnAllImages(itemList));
        return "itemList";
    }

    private List<String> returnAllImages(List<Item> itemList) {
        return itemList.stream().map(item -> {
            if (item.getImage().length == 0) {
                return "";
            } else {
                return new String(Base64.getEncoder().encode(item.getImage()));
            }
        }).collect(Collectors.toList());
    }


    @GetMapping(path = "/addItem")
    public String add(Model model) {
        Item item = new Item();
        List<Category> categories = categoryService.getAllList();
        model.addAttribute("item", item);
        model.addAttribute("categories", categories);
        return "addItemForm";
    }

    @PostMapping(path = "/addItem")
    public String add(Item item, @RequestParam("photo") MultipartFile file) {
        String name = file.getName();
        try {
            byte[] bytes = file.getBytes();
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(name + "-uploaded")));
            stream.write(bytes);
            stream.close();
            item.setImage(bytes);
        } catch (Exception e) {
            System.out.println("File has not been added.");
        }
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
        return "itemEdit";
    }

    @PostMapping(path = "/edit")
    public String setItemsEdit(ItemDTO item) {
        Optional<Item> editedItem = itemService.find(item.getId());
        if (editedItem.isPresent()) {
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
        Optional<Item> itemOptional = itemService.find(id);

        if (itemOptional.isPresent()) {
            Item item = itemOptional.get();
            model.addAttribute("item", item);
            model.addAttribute("imageBase64", new String(Base64.getEncoder().encode(item.getImage())));
        } else {
            return "redirect:/item/itemList";
        }
        return "itemDetails";
    }

}