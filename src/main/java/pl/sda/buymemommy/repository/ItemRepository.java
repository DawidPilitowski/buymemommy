package pl.sda.buymemommy.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.sda.buymemommy.model.Category;
import pl.sda.buymemommy.model.Gender;
import pl.sda.buymemommy.model.Item;

import java.util.List;
import java.util.Set;


public interface ItemRepository extends JpaRepository<Item, Long> {

    Set<Item> findAllByItemName(String name);

    List<Item> findAllByItemNameContains(String word);



    List<Item> findAllByCategoryList(List<Category> categories);

    List<Item> findAllByAgeFromGreaterThan (int age);

    List<Item> findAllByAgeFromGreaterThanAndCategoryListAndItemNameContainsAndGender(int age, List<Category> categories, String word, Gender gender);
    List<Item> findAllByAgeFromGreaterThanAndCategoryListAndItemNameContains(int age, List<Category> categories, String word);
}

