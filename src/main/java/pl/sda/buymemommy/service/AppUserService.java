package pl.sda.buymemommy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.sda.buymemommy.model.AppUser;
import pl.sda.buymemommy.model.dto.AppUserEditPasswordDTO;
import pl.sda.buymemommy.model.dto.AppUserEditProfileDTO;
import pl.sda.buymemommy.model.dto.AppUserRegisterDTO;
import pl.sda.buymemommy.model.dto.LoggedInUserDTO;
import pl.sda.buymemommy.repository.AppUserRepository;

import java.util.Optional;

@Service
public class AppUserService {
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private AppUserRepository appUserRepository;

    public boolean registerUser(AppUserRegisterDTO dto) {
        Optional<AppUser> appUser = appUserRepository.findByUsername(dto.getUsername());
        if (appUser.isPresent()) {
            return false;
        }
        AppUser newUser = new AppUser(dto.getUsername(),
                bCryptPasswordEncoder.encode(dto.getPassword()));

        appUserRepository.save(newUser);
        return true;
    }

    public AppUser findUserByUsername(String username) {
        return appUserRepository.findOneByUsername(username);
    }

    public AppUser getLoggedInUser() {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        return appUserRepository.findOneByUsername(name);
    }

    public void updateUserDTO(AppUserEditProfileDTO modifyUserDTO, byte[] bytes) {
        Long id = modifyUserDTO.getId();

        Optional<AppUser> optionalAppUser = appUserRepository.findById(id);

        if (optionalAppUser.isPresent()) {
            AppUser appUser = optionalAppUser.get();
            appUser.setAddress(modifyUserDTO.getAddress());
            appUser.setName(modifyUserDTO.getName());
            appUser.setEmail(modifyUserDTO.getEmail());
            appUser.setSurname(modifyUserDTO.getSurname());
            appUser.setBankNumberAccount(modifyUserDTO.getBankNumberAccount());
            appUser.setAvatar(bytes);

            appUserRepository.save(appUser);
        }
    }
//    public void deleteUser(Long id) {
//        appUserRepository.deleteById(id);
//    }

    public Optional<AppUser> find(Long id) {
        return appUserRepository.findById(id);
    }

    public void editUserPasswordDTO(AppUserEditPasswordDTO userEditPasswordDTO) {
    }

    public void removeUser(Long id) {
        appUserRepository.deleteById(id);
    }

    public Optional<LoggedInUserDTO> getLoggedInUserDTO() {
        LoggedInUserDTO loggedInUserDTO = null;
        String loggedInUserName = getLoggedInUserName();
        Optional<AppUser> optionalLoggedInUser = appUserRepository.findByUsername(loggedInUserName);
        if(optionalLoggedInUser.isPresent()) {
            AppUser loggedInUser = optionalLoggedInUser.get();
            loggedInUserDTO = new LoggedInUserDTO();
            loggedInUserDTO.setId(loggedInUser.getId());
            loggedInUserDTO.setUsername(loggedInUser.getUsername());
            loggedInUserDTO.setAvatar(loggedInUser.getAvatar());
        }
        return Optional.ofNullable(loggedInUserDTO);
    }

    private String getLoggedInUserName() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
