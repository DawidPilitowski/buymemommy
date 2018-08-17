package pl.sda.buymemommy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.sda.buymemommy.model.RegisterAppUserDTO;
import pl.sda.buymemommy.service.AppUserService;

@Controller
public class UserController {

//    controller: mappingi dodawania (get, post)

    @Autowired
    private AppUserService appUserService;
    
    @GetMapping(path = "/login")
    public String login() {
        return "login";
    }


    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("user_dto", new RegisterAppUserDTO());
        return "userRegister";
    }
    @PostMapping("/register")
    public String register(Model model, RegisterAppUserDTO dto){
        if (!dto.getConfirm_password().equals(dto.getPassword())){
            model.addAttribute("user_dto",dto);
            model.addAttribute("error_message",
                    "Nieprawidłowe hasło!");
            return "userRegister";
        }
        if (!appUserService.registerUser(dto)){
            model.addAttribute("user_dto", dto);

            model.addAttribute("error_message",
                    "Login jest już zajęty!");
            return "userRegister";
        }return "redirect:/login";
    }


}
