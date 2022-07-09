package com.example.common.repository;

import com.example.entities.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends JpaRepository<Tag, Integer> {

    @Modifying
    @Query(value = "INSERT INTO tag (name) VALUES (:name)",nativeQuery = true)
    void createTag(@Param(value = "name") String name);

    @Modifying
    @Query(value = "INSERT INTO tag_item (item_id, tag_id) VALUES (:item_id, :tag_id)",nativeQuery = true)
    void setTagItem(@Param(value = "item_id") int itemId,
                           @Param(value = "tag_id") int tagId);

    @Query(value = "SELECT id FROM tag WHERE name=:name", nativeQuery = true)
    int findTagIdByName(@Param(value = "name") String name);
}
