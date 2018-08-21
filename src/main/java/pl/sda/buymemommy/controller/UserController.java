package pl.sda.buymemommy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pl.sda.buymemommy.model.RegisterUserDTO;
import pl.sda.buymemommy.service.UserService;

@Controller
public class UserController {

//    controller: mappingi dodawania (get, post)

    @Autowired
    private UserService userService;
    
    @GetMapping(path = "/login")
    public String login() {
        return "login";
    }


    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("user_dto", new RegisterUserDTO());
        return "register";
    }
    @PostMapping("/register")
    public String register(Model model, RegisterUserDTO dto){
        if (!dto.getConfirm_password().equals(dto.getPassword())){
            model.addAttribute("user_dto",dto);
            model.addAttribute("error_message",
                    "Nieprawidłowe hasło!");
            return "register";
        }
        if (!userService.registerUser(dto)){
            model.addAttribute("user_dto", dto);

            model.addAttribute("error_message",
                    "Login jest już zajęty!");
            return "register";
        }return "redirect:/login";
    }


}
