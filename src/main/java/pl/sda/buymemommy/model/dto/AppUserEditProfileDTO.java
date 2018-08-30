package pl.sda.buymemommy.model.dto;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Lob;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppUserEditProfileDTO {

    private Long id;
    private String name;
    private String surname;
    private String email;
    private String address;
    private String bankNumberAccount;

    @Lob
    @Column
    private byte[] avatar;
}
