package com.example.task5.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.Objects;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
@Table(name = "comments")
public class Comment extends AbstractEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "last_update_date")
    private Timestamp lastUpdateDate;

    @Column(name = "itemId")
    private int itemId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return itemId == comment.itemId && Objects.equals(name, comment.name) && Objects.equals(lastUpdateDate, comment.lastUpdateDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, lastUpdateDate, itemId);
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastUpdateDate=" + lastUpdateDate +
                ", itemId=" + itemId +
                '}';
    }
}
