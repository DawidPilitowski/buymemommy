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
import pl.sda.buymemommy.model.dto.LoggedInUserDTO;
import pl.sda.buymemommy.service.AppUserService;

import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Controller
@ControllerAdvice
@Scope("session")
public class IndexController {
    @Autowired
    private CategoryComponent categoryComponent;
    @Autowired
    private AppUserService appUserService;

    @ModelAttribute
    public void addAttributes(Model model) {
        HashMap<MainCategory, List<Subcategory>> categoryMap = categoryComponent.getCategoryMap();
        model.addAttribute("categories", categoryMap);

        Optional<LoggedInUserDTO> optionalLoggedInUserDTO = appUserService.getLoggedInUserDTO();
        if(optionalLoggedInUserDTO.isPresent()) {
            LoggedInUserDTO loggedInUserDTO = optionalLoggedInUserDTO.get();
            model.addAttribute("loggedInUserDTO", loggedInUserDTO);
            model.addAttribute("loggedInUserAvatar", loggedInUserDTO.getAvatar() == null ? "" : new String(Base64.getEncoder().encode(loggedInUserDTO.getAvatar())));
            // TODO : USUNAC Z KODU --->
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            model.addAttribute("authentication", authentication);
            // TODO : <---
        }
    }

    @GetMapping(path = "/")
    public String getIndex(Model model) {
        model.addAttribute("title", "buymemommy");

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
}
