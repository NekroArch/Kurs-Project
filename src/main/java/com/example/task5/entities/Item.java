package com.example.task5.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "item")
public class Item extends AbstractEntity{

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "collection_id")
    private Collection itemInCollection;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "tag_item",
            joinColumns = {@JoinColumn(name = "item_id") },
            inverseJoinColumns = {@JoinColumn(name = "tag_id")})
    private List<Tag> tags;
}
