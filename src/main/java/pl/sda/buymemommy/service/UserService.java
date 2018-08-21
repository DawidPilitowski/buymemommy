package pl.sda.buymemommy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import pl.sda.buymemommy.model.RegisterUserDTO;
import pl.sda.buymemommy.model.UserModel;
import pl.sda.buymemommy.repository.IUserRepository;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private IUserRepository appUserRepository;

    public boolean registerUser(RegisterUserDTO dto) {
        Optional<UserModel> appUser = appUserRepository.findByUsername(dto.getUsername());
        if (appUser.isPresent()){
            return false;
        }
        UserModel newUser = new UserModel(dto.getUsername(),
                bCryptPasswordEncoder.encode(dto.getPassword()));

        appUserRepository.save(newUser);
        return true;
    }

//    rejestracja użytkownika
}
