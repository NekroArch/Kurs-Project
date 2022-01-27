package com.example.task5.repository;


import com.example.task5.entities.Collection;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CollectionRepository extends JpaRepository<Collection, Integer> {

    @Query(value = "FROM Collection")
    @EntityGraph(attributePaths = {"items","attributes"})
    List<Collection> getAllCollection();

    @Query(value = "FROM Collection WHERE user_id =:user_id")
    @EntityGraph(attributePaths = {"items","attributes"})
    List<Collection> getCollectionByUserId(@Param(value = "user_id") int userId);

    @Modifying
    @Query(value = "DELETE FROM collection WHERE id = :id", nativeQuery = true)
    void deleteCollection(@Param(value = "id") int id);

    @Query(value = "SELECT id FROM collection WHERE name=:name", nativeQuery = true)
    int findCollectionIdByName(@Param(value = "name") String name);
}
