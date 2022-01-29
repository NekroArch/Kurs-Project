package com.example.task5.repository;

import com.example.task5.entities.Collection;
import com.example.task5.entities.ItemAttribute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ItemAttributeRepository extends JpaRepository<ItemAttribute, Integer> {

    @Modifying
    @Query(name = "INSERT INTO item_attribute (value, item_id, attribute_id) VALUES (:value, :item_id, :attribute_id)",nativeQuery = true)
    public void createItemAttribute(@Param(value = "value") String value,
                     @Param(value = "item_id") int itemId,
                     @Param(value = "attribute_id") int attributeId);

    @Modifying
    @Query(name = "UPDATE item_attribute SET value=:value WHERE id=:id",nativeQuery = true)
    public void save(@Param(value = "value") String value,
                     @Param(value = "id") int id);


}
