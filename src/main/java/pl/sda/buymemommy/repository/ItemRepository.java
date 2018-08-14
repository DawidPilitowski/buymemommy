package pl.sda.buymemommy.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.buymemommy.model.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
