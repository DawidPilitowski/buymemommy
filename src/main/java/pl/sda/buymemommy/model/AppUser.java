package pl.sda.buymemommy.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
//@NoArgsConstructor
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;

    private String name;
    private String surname;
    private String address;
    private String email;
    private String role = "ROLE_USER";

    private LocalDate registerDate;

    public AppUser(String username, String password) {
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
