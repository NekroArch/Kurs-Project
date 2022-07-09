package com.example.common.repository;


import com.example.entities.Collection;
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
    @EntityGraph(attributePaths = {"items","attributes","items.itemAttributes"})
    List<Collection> getCollectionByUserId(@Param(value = "user_id") int userId);

    @Modifying
    @Query(value = "DELETE FROM collection WHERE id = :id", nativeQuery = true)
    void deleteCollection(@Param(value = "id") int id);

    @Query(value = "SELECT id FROM collection WHERE name=:name", nativeQuery = true)
    int findCollectionIdByName(@Param(value = "name") String name);

    @Query(value = "FROM Collection as c JOIN User as u on c.user=u WHERE u.name=:userName")
    @EntityGraph(attributePaths = {"items","attributes","items.itemAttributes"})
    List<Collection> findCollectionByUserName(@Param("userName") String userName);

    @Query(value = "SELECT * FROM collection WHERE to_tsvector(name) " +
            "|| to_tsvector(description) " +
            "|| to_tsvector(topic) " +
            "@@ to_tsquery(:text); ",
            nativeQuery = true)
    List<Collection> findCollectionByText (@Param(value = "text") String text);

}
