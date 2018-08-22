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
//    @GetMapping(path="/addCategory")
//    public String addCategory(Model model) {
//        Category category = new Category();
//        model.addAttribute("category", category);
//        return "addCategory";
//    }
//    @PostMapping(path="/addCategory")
//    public String showCategory(Model model, Category category) {
//        categoryService.saveCategory(category);
//        return "redirect:/categoryList";
//    }
//    @GetMapping(path = "/remove/{id}")
//    public String remove(@PathVariable(name="id") Long id){
//        categoryService.removeCategory(id);
//        return "redirect:/categoryList";
//    }
//    @GetMapping(path = "/details/{id}")
//    public String details(Model model, @PathVariable(name="id")Long id){
//        Optional<Category> categoryOptional= categoryService.find(id);
//        if (categoryOptional.isPresent()){
//            model.addAttribute("category", categoryOptional.get());
//            return "categoryDetails";
//        }
//        return "error";
//    }

}
