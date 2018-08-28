package pl.sda.buymemommy.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MainCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nameCategory;

    public String getURN() {
        String urn = nameCategory.replaceAll(",|\\.|'|-", "");
        urn = urn.replaceAll(" ", "-");
        return urn;
    }
//    private List<Subcategory> subcategoryList;
}
