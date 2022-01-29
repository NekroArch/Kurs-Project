package com.example.task5.repository;

import com.example.task5.entities.Collection;
import com.example.task5.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {

    @Modifying
    @Query(value = "INSERT INTO item (name, collection_id) VALUES (:name, :collectionId)", nativeQuery = true)
    void createItem(@Param(value = "name") String name,
                          @Param(value = "collectionId") int collectionId);

    @Query(value = "SELECT id FROM item WHERE name=:name", nativeQuery = true)
    int findItemIdByName(@Param(value = "name") String name);
}
