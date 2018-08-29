package pl.sda.buymemommy.model.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AppUserRegisterDTO {

    private String username;
    private String password;
    private String email;
    private String confirm_password;


}
