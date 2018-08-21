package pl.sda.buymemommy.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String itemName;
    private String description;
    private String price;
    private Gender gender;
    private int ageFrom;
    private int ageTo;

    @ManyToMany
    List<Category> categoryList;


}
