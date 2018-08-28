package pl.sda.buymemommy.model.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppUserEditProfileDTO {

    private Long id;
    private String name;
    private String surname;
    private String email;
    private String address;
}
