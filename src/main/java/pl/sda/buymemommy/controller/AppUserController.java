package pl.sda.buymemommy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.sda.buymemommy.model.AppUser;
import pl.sda.buymemommy.model.dto.AppUserEditPasswordDTO;
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
        return "register";
    }

    @PostMapping("/register")
    public String register(Model model, AppUserRegisterDTO dto) {
        if (!dto.getConfirm_password().equals(dto.getPassword())) {
            model.addAttribute("user_dto", dto);
            model.addAttribute("error_message",
                    "Nieprawidłowe hasło!");
            return "register";
        }
        if (!appUserService.registerUser(dto)) {
            model.addAttribute("user_dto", dto);

            model.addAttribute("error_message",
                    "Login jest już zajęty!");
            return "register";
        }
        System.out.println(dto.toString());
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
//        System.out.println(userEditProfileDTO.toString());
        return "edit";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String editPost(AppUserEditProfileDTO userEditProfileDTO, @PathVariable(name = "id")
            Long id, BindingResult result) {

        if (result.hasFieldErrors("id")) {
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
            return "profile";
        }
        return "redirect:/error";
    }

    @PostMapping("/profile")
    public String deleteUserAccount(@PathVariable(name = "id") Long id) {
        appUserService.deleteUser(id);
        return "redirect:/register";
    }

    @GetMapping(path = "/profile/editPassword/{id}")
    public String showEditUserPasswordForm(@PathVariable("id") Long id, Model model) {

        AppUser user;
        AppUser logedUser = appUserService.getLoggedInUser();

        if (id == null) {
            return "userNotFound";
        }
        if (!logedUser.getId().equals(id)) {
            return "permission-denied";
        }
        if (logedUser.isAdmin() || logedUser.getId().equals(id)) {
            Optional<AppUser> found = appUserRepository.findById(id);
            if (found.isPresent()) {
                user = found.get();
            } else {
                return "error";
            }
        } else {
            user = logedUser;
        }
        AppUserEditPasswordDTO appUserEditPasswordDTO = new AppUserEditPasswordDTO();
        appUserEditPasswordDTO.setId(user.getId());
        appUserEditPasswordDTO.setPassword(user.getPassword());
        appUserEditPasswordDTO.setConfirm_password(user.getPassword());

        model.addAttribute("userEditPasswordDTO", appUserEditPasswordDTO);
        return "editPassword";
    }

    @PostMapping(path = "/profile/editPassword/{id}")
    public String editUserPassword(AppUserEditPasswordDTO userEditPasswordDTO,
                                   @PathVariable("id") Long id,
                                   BindingResult bindingResult) {

        appUserService.editUserPasswordDTO(userEditPasswordDTO);

        return "redirect:/login";
    }
    @GetMapping(path = "")
    public String removeUser(@PathVariable(name = "id")Long id){
        AppUser loggedInUser = appUserService.getLoggedInUser();
        if (!loggedInUser.getId().equals(id)) {
            return "permission-denied";
        }else if(!loggedInUser.isAdmin()){
            return "permission-denied";
        }else {
            appUserService.removeUser(id);
            return "redirect:/register";
        }
    }


}