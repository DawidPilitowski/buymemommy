package pl.sda.buymemommy.model.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppUserEditPasswordDTO {
    private Long id;
    private String password;
    private String confirm_password;
}
