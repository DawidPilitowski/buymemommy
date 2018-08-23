package pl.sda.buymemommy.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppUserRegisterDTO {

    private String username;
    private String password;
    private String confirm_password;

}
