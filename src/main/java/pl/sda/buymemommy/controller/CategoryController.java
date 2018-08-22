package pl.sda.buymemommy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.sda.buymemommy.model.Category;
import pl.sda.buymemommy.service.CategoryService;

import java.util.List;
import java.util.Optional;

@Controller
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping(path = "/categoryList")
    public String list(Model model){
        List<Category> categoryList = categoryService.getAllList();
        model.addAttribute("categoryList", categoryList);
        return "categoryList";
    }


}
