package pl.sda.buymemommy.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Map;

@Entity
@Data
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Size(min=2, max=30, message = "Nazwa użytkownika min 2 znaki max 30")
    private String username;

    @NotNull
    @Size(min = 2, max = 50 ,message = "Błędne hasło min 2 znaki max 50")
    private String password;

    private String name;
    private String surname;

    public AppUser(String username, String password) {
        this.username = username;
        this.password = password;
    }

    //todo WISHLIST
//    @OneToMany
//    private Map<Item,id> wishList;
//    private List<Item> wishList;

}
