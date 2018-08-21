package pl.sda.buymemommy.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Entity
@Data
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String password;

    private String name;
    private String surname;
    private String userSurname;
    private String address;

    private LocalDate registerDate;

    public UserModel(String username, String password) {
        this.username = username;
        this.password = password;
    }

    //todo WISHLIST
//    @OneToMany
//    private Map<Item,id> wishList;
//    private List<Item> wishList;

}
