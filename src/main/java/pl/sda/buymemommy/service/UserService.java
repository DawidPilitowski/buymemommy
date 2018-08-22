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
    @Autowired
    private HttpSession httpSession;
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
        return getLoggedInUser(false);
    }

    public UserModel getLoggedInUser(Boolean forceFresh) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        UserModel user = (UserModel) httpSession.getAttribute(CURRENT_USER_KEY);
        if (forceFresh || httpSession.getAttribute(CURRENT_USER_KEY) == null) {
            user = this.appUserRepository.findOneByUsername(userName);
            httpSession.setAttribute(CURRENT_USER_KEY, user);
        }
        return user;
    }

    public void updateUser(UserModel user) {
        updateUser(user.getUsername(), user);
    }

    public void updateUser(String userName, UserModel newData) {
        this.appUserRepository.updateUser(
                userName, newData.getEmail(),
                newData.getAddress(), newData.getSurname());
    }

    public void deleteUser(Long id) {
        appUserRepository.deleteById(id);
    }

    public Optional<UserModel> find(Long id) {
        return appUserRepository.findById(id);
    }

//    rejestracja użytkownika
}
