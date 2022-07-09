package com.example.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "attribute")
public class Attribute extends AbstractEntity{

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @ManyToOne
    @JoinColumn(name = "collection_id")
    private Collection attributeInCollection;

    public Attribute(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public Attribute(String name) {
        this.name = name;
    }

    public Attribute(String attributeName, Collection collection) {
        this.name = attributeName;
        this.attributeInCollection = collection;
    }
}
