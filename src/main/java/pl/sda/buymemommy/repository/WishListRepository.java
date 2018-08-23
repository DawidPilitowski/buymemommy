package pl.sda.buymemommy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.sda.buymemommy.model.WishList;

@Repository
public interface WishListRepository extends JpaRepository<WishList,Long> {
}
