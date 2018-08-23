package pl.sda.buymemommy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pl.sda.buymemommy.model.dto.AppUserRegisterDTO;
import pl.sda.buymemommy.service.AppUserService;

@Controller
public class AppUserController {

//    controller: mappingi dodawania (get, post)

    @Autowired
    private AppUserService appUserService;
    
    @GetMapping(path = "/login")
    public String login() {
        return "oldLogin";
    }


    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("user_dto", new AppUserRegisterDTO());
        return "oldRegister";
    }
    @PostMapping("/register")
    public String register(Model model, AppUserRegisterDTO dto){
        if (!dto.getConfirm_password().equals(dto.getPassword())){
            model.addAttribute("user_dto",dto);
            model.addAttribute("error_message",
                    "Nieprawidłowe hasło!");
            return "oldRegister";
        }
        if (!appUserService.registerUser(dto)){
            model.addAttribute("user_dto", dto);

            model.addAttribute("error_message",
                    "Login jest już zajęty!");
            return "oldRegister";
        }return "redirect:/login";
    }


}
