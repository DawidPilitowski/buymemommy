package pl.sda.buymemommy.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.buymemommy.model.Item;

import java.util.List;
import java.util.Set;

public interface ItemRepository extends JpaRepository<Item, Long> {

    public Set<Item> findAllByItemName(String name);
}
