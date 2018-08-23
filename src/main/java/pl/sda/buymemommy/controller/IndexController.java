package pl.sda.buymemommy.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.sda.buymemommy.model.Category;
import pl.sda.buymemommy.service.CategoryService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping(path = "/")
    public String getIndex(Model model) {
        List<Category> categoryList = categoryService.getAllList();
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("title", "buymemommy");
        model.addAttribute("loggedUserDto", new LoggedUserDto("Bruce", "Wayne", "/img/batman.jpg"));
        return "index";
    }

    @GetMapping(path = "/charts")
    public String getCharts() {
        return "charts";
    }

    @GetMapping(path = "/index2")
    public String getIndex2() {
        return "index";
    }

    // TODO : USUNAC, stworzyc klasy

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    private class LoggedUserDto {
        private String username;
        private String surname;
        private String avatarUrl;
    }
}
