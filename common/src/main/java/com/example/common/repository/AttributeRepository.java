package com.example.common.repository;

import com.example.entities.Attribute;
import com.example.entities.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface AttributeRepository extends JpaRepository<Attribute, Integer> {

    @Query(value = "SELECT id FROM attribute WHERE name=:name", nativeQuery = true)
    int findAttributeIdByName(@Param(value = "name") String name);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO item_attribute (value, item_id, attribute_id) VALUES (:value, :item_id, :attribute_id)", nativeQuery = true)
    void addAttributeValue(@Param(value = "value") String value,
                                  @Param(value = "item_id") int item_id,
                                  @Param(value = "attribute_id") int attribute_id);

}
