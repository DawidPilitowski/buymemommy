package pl.sda.buymemommy.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoggedInUserDTO {
    private Long id;
    private String username;
    private byte[] avatar;
}