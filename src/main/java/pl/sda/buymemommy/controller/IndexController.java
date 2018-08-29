package pl.sda.buymemommy.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import pl.sda.buymemommy.components.CategoryComponent;
import pl.sda.buymemommy.model.MainCategory;
import pl.sda.buymemommy.model.Subcategory;

import java.util.HashMap;
import java.util.List;

@Controller
@ControllerAdvice
@Scope("session")
public class IndexController {
    @Autowired
    private CategoryComponent categoryComponent;

    @ModelAttribute
    public void addAttributes(Model model) {
        HashMap<MainCategory, List<Subcategory>> categoryMap = categoryComponent.getCategoryMap();
        model.addAttribute("categories", categoryMap);
        // TODO --->
        String loggedInUserName = "difolt";
        String loggedInPrincipal = "difolt";
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        authentication.getPrincipal();
        if (authentication != null) {
            loggedInUserName = authentication.getName();
            loggedInPrincipal = authentication.getPrincipal().toString();
        }
        model.addAttribute("loggedInUserDTO", loggedInUserName);
        model.addAttribute("loggedInPrincipal", loggedInPrincipal);
        // TODO <---
    }

    @GetMapping(path = "/")
    public String getIndex(Model model) {
        model.addAttribute("title", "buymemommy");
        model.addAttribute("loggedUserDto", new LoggedUserDto("Bruce", "Wayne", "/img/batman.jpg"));
        return "index";
    }

    // TODO : REMOVE --->
    @GetMapping(path = "/logtest")
    public String getLoginPage() {
        return "login";
    }

    @GetMapping(path = "/registertest")
    public String getRegisterPage() {
        return "register";
    }

    @GetMapping(path = "/tabelki")
    public String getTables() {
        return "itemList.html";
    }

    // TODO : <--- REMOVE

    @GetMapping(path = "/charts")
    public String getCharts() {
        return "charts";
    }

    @GetMapping(path = "/index2")
    public String getIndex2() {
        return "index";
    }

    @GetMapping(path = "/forms")
    public String getPath(){
        return "forms";
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
