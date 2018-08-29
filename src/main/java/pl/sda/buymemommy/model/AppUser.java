package pl.sda.buymemommy.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
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
    private String bankNumberAccount;

    @Lob
    @Column
    private byte[] avatar;


    public AppUser(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Boolean isAdmin() {
        return this.role.equals("ROLE_ADMIN");
    }

    //TODO WISHLIST



}
