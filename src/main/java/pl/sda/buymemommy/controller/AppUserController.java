package pl.sda.buymemommy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.sda.buymemommy.model.AppUser;
import pl.sda.buymemommy.model.dto.AppUserEditProfileDTO;
import pl.sda.buymemommy.model.dto.AppUserRegisterDTO;
import pl.sda.buymemommy.repository.AppUserRepository;
import pl.sda.buymemommy.service.AppUserService;

import java.util.Optional;

@Controller
public class AppUserController {

    //TODO !!!!!! Ogarnąć edycję profilu bo podczas oedycji odczytuje się hasło i ustawia na null  !!!!!!!!!!

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private AppUserService appUserService;

    @GetMapping(path = "/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user_dto", new AppUserRegisterDTO());
        return "oldRegister";
    }

    @PostMapping("/register")
    public String register(Model model, AppUserRegisterDTO dto) {
        if (!dto.getConfirm_password().equals(dto.getPassword())) {
            model.addAttribute("user_dto", dto);
            model.addAttribute("error_message",
                    "Nieprawidłowe hasło!");
            return "oldRegister";
        }
        if (!appUserService.registerUser(dto)) {
            model.addAttribute("user_dto", dto);

            model.addAttribute("error_message",
                    "Login jest już zajęty!");
            return "oldRegister";
        }
        return "redirect:/login";
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable("id") Long id) {
        AppUser u;
        AppUser loggedInUser = appUserService.getLoggedInUser();
        if (id == 0) {
            id = loggedInUser.getId();
        }
        if (!loggedInUser.getId().equals(id)) {
            return "permission-denied";
        }
        if (loggedInUser.isAdmin() || loggedInUser.getId().equals(id)) {
            Optional<AppUser> found = appUserRepository.findById(id);
            if (found.isPresent()) {
                u = found.get();
            } else {
                return "error";
            }
        } else {
            u = loggedInUser;
        }
        AppUserEditProfileDTO userEditProfileDTO = new AppUserEditProfileDTO();
        userEditProfileDTO.setId(u.getId());

        userEditProfileDTO.setEmail(u.getEmail());
        userEditProfileDTO.setName(u.getName());
        userEditProfileDTO.setSurname(u.getSurname());
        userEditProfileDTO.setAddress(u.getAddress());

        model.addAttribute("userDTO", userEditProfileDTO);//user dto pobierać 3 elementy zmienić w widoku
        System.out.println(userEditProfileDTO.toString());
        return "edit";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String editPost(AppUserEditProfileDTO userEditProfileDTO, @PathVariable(name = "id") Long id, BindingResult result) {

        if (result.hasFieldErrors("email")) {
            return "edit";
        }
            appUserService.updateUserDTO(userEditProfileDTO);

        //        if (appUserService.getLoggedInUser().isAdmin()) {
//        } else {
//            appUserService.updateUser(appUserService.getLoggedInUser().getUsername(), user);
//        }

        return "redirect:/profile";
    }

    @GetMapping(path = "/profile")
    public String showProfilePage(Model model) {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        AppUser userByUsername = appUserService.findUserByUsername(username);
        if (userByUsername != null) {
            model.addAttribute("profile", userByUsername);
            return "oldProfile";
        }
        return "redirect:/error";
    }

    @PostMapping("/profile")
    public String deleteUserAccount(@PathVariable(name = "id") Long id) {
        appUserService.deleteUser(id);
        return "redirect:/register";
    }
}