package pl.sda.buymemommy.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemDTO {

    private Long id;
    private String itemName;
    private String description;
    private Double price;

}
