package pl.sda.buymemommy.model;

import lombok.AllArgsConstructor;
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
//@NoArgsConstructor
@AllArgsConstructor
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String password;

    private String surname;
    private String address;
    private String email;
    private String role= "ROLE_USER";

    private LocalDate registerDate;

    public UserModel(String username, String password) {
        this.username = username;
        this.password = password;
    }
    public Boolean isAdmin() {
        return this.role.equals("ROLE_ADMIN");
    }


    //todo WISHLIST
//    @OneToMany
//    private Map<Item,id> wishList;
//    private List<Item> wishList;

}
