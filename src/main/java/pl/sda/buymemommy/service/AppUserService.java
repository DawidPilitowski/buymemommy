package pl.sda.buymemommy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.sda.buymemommy.model.dto.AppUserRegisterDTO;
import pl.sda.buymemommy.model.AppUser;
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
        if (appUser.isPresent()){
            return false;
        }
        AppUser newUser = new AppUser(dto.getUsername(),
                bCryptPasswordEncoder.encode(dto.getPassword()));

        appUserRepository.save(newUser);
        return true;
    }

//    rejestracja użytkownika
}
