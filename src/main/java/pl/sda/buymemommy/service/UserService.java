package pl.sda.buymemommy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.sda.buymemommy.model.RegisterUserDTO;
import pl.sda.buymemommy.model.UserModel;
import pl.sda.buymemommy.repository.IUserRepository;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private IUserRepository appUserRepository;
    public final String CURRENT_USER_KEY = "CURRENT_USER";


    public boolean registerUser(RegisterUserDTO dto) {
        Optional<UserModel> appUser = appUserRepository.findByUsername(dto.getUsername());
        if (appUser.isPresent()) {
            return false;
        }
        UserModel newUser = new UserModel(dto.getUsername(),
                bCryptPasswordEncoder.encode(dto.getPassword()));

        appUserRepository.save(newUser);
        return true;
    }

    public UserModel findUserByUsername(String username) {
        return appUserRepository.findOneByUsername(username);
    }


    public UserModel getLoggedInUser() {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        return appUserRepository.findOneByUsername(name);
    }


    public void updateUser(UserModel user) {
        updateUser(user.getUsername(), user);
    }

    public void updateUser(String userName, UserModel newData) {
//        this.appUserRepository.updateUser(
//                userName, newData.getEmail(),
//                newData.getAddress(), newData.getSurname());
        appUserRepository.save(newData);
    }

    public void deleteUser(Long id) {
        appUserRepository.deleteById(id);
    }

    public Optional<UserModel> find(Long id) {
        return appUserRepository.findById(id);
    }

//    rejestracja użytkownika
}
