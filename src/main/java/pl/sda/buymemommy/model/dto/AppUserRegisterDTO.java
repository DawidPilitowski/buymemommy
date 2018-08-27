package pl.sda.buymemommy.model.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AppUserRegisterDTO {

    private String username;
    private String password;
    private String confirm_password;

}
