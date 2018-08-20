package pl.sda.buymemommy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.sda.buymemommy.model.Category;
import pl.sda.buymemommy.model.Item;
import pl.sda.buymemommy.service.CategoryService;
import pl.sda.buymemommy.service.ItemService;

import java.util.List;

@Controller
@RequestMapping(path = "/item/")
public class ItemController {


    @Autowired
    private ItemService itemService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping(path = "/itemList")
    public String itemList(Model model){
        List<Item> itemList= itemService.getAllItems();
        model.addAttribute("itemList", itemList);
        return "itemList";
    }
    @GetMapping(path="/addItem")
    public String add(Model model){
        Item item= new Item();
        List<Category> categories= categoryService.getAllList();
        model.addAttribute("item", item);
        model.addAttribute("categories", categories);
        return "addItem";
    }
    @PostMapping(path = "/addItem")
    public String add(Item item){
       itemService.addProduct(item);
            return "redirect:/itemList";
        }


}
