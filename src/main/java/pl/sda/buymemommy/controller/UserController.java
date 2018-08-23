package pl.sda.buymemommy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.sda.buymemommy.model.RegisterUserDTO;
import pl.sda.buymemommy.model.UserModel;
import pl.sda.buymemommy.repository.IUserRepository;
import pl.sda.buymemommy.service.UserService;

import java.util.Optional;

@Controller
public class UserController {

    @Autowired
    private IUserRepository iUserRepository;

    @Autowired
    private UserService userService;

    @GetMapping(path = "/login")
    public String loginGet() {
        return "/login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user_dto", new RegisterUserDTO());
        return "/register";
    }

    @PostMapping("/register")
    public String register(Model model, RegisterUserDTO dto) {
        if (!dto.getConfirm_password().equals(dto.getPassword())) {
            model.addAttribute("user_dto", dto);
            model.addAttribute("error_message",
                    "Nieprawidłowe hasło!");
            return "/register";
        }
        if (!userService.registerUser(dto)) {
            model.addAttribute("user_dto", dto);

            model.addAttribute("error_message",
                    "Login jest już zajęty!");
            return "/register";
        }
        return "redirect:/login";
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable("id") Long id) {
        UserModel u;
        UserModel loggedInUser = userService.getLoggedInUser();
        if (id == 0) {
            id = loggedInUser.getId();
        }
        if (!loggedInUser.getId().equals(id)) {
            return "premission-denied";
        }
        if (loggedInUser.isAdmin() || loggedInUser.getId().equals(id)) {
            Optional<UserModel> found = iUserRepository.findById(id);
            if (found.isPresent()) {
                u = found.get();
            } else {
                return "error";
            }
        } else {
            u = loggedInUser;
        }
        model.addAttribute("user", u);

        return "edit";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String editPost(UserModel user, @PathVariable(name = "id") Long id, BindingResult result) {
        if (result.hasFieldErrors("email")) {
            return "/edit";
        }
        if (userService.getLoggedInUser().isAdmin()) {
            userService.updateUser(user);
        } else {
            userService.updateUser(userService.getLoggedInUser().getUsername(), user);
        }
        return "redirect:/profile";
    }


    @GetMapping(path = "/profile")
    public String showProfilePage(Model model) {

//        request.getUserPrincipal().getName()
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        UserModel userByUsername = userService.findUserByUsername(username);
        if (userByUsername != null) {
            model.addAttribute("profile", userByUsername);
            return "profile";
        }
        return "redirect:/error";
    }

    @PostMapping("/profile")
    public String deleteUserAccount(@PathVariable(name = "id") Long id) {
        userService.deleteUser(id);
        return "redirect:/register";
    }
}
