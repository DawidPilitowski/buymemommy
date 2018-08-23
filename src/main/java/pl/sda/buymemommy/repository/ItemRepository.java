package pl.sda.buymemommy.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.sda.buymemommy.model.Category;
import pl.sda.buymemommy.model.Item;

import java.util.List;
import java.util.Set;


public interface ItemRepository extends JpaRepository<Item, Long> {

    public Set<Item> findAllByItemName(String name);

//    @Query("SELECT Item from item where item_name like :nameL%")
//    public List<Item> findByItemNameLike(@Param("nameL")String nameLike);

//    List<Item> findAllByItemNameLike(String nameLike);
    List<Item> findAllByItemNameContains(String word);

    List<Item> findAllByCategoryList(List<Category> categories);
}
