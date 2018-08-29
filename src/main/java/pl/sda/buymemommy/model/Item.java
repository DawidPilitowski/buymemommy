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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String itemName;
    private String description;
    private int itemLimit;
    private Double price;
    private Gender gender;
    private int ageFrom;
    private int ageTo;

    @Lob
    @Column
    private byte[] image;

    @ManyToOne
    private AppUser sellingUser ;

    @ManyToMany
    List<Category> categoryList;
}
