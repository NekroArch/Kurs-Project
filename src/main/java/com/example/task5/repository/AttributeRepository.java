package com.example.task5.repository;

import com.example.task5.entities.Attribute;
import com.example.task5.entities.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AttributeRepository extends JpaRepository<Attribute, Integer> {

    @Query(value = "SELECT id FROM attribute WHERE name=:name", nativeQuery = true)
    int findAttributeIdByName(@Param(value = "name") String name);

}
