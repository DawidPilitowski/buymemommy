package pl.sda.buymemommy.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.sda.buymemommy.model.Category;
import pl.sda.buymemommy.model.Gender;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemDto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String itemName;
    private String description;
    private String price;

}
