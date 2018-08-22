package pl.sda.buymemommy.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.sda.buymemommy.model.Item;

import java.util.List;
import java.util.Set;


public interface ItemRepository extends JpaRepository<Item, Long> {

    public Set<Item> findAllByItemName(String name);

//    @Query("SELECT item from buymemommy.item where item_name like %item_name%")
//    public List<Item> findByItemNameLike(String nameLike);
}
