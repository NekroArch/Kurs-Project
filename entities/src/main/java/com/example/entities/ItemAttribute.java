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
@Table(name = "item_attribute")
public class ItemAttribute extends AbstractEntity{

    @Column(name = "value")
    private String value;

    @ManyToOne()
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne()
    @JoinColumn(name = "attribute_id")
    private Attribute attribute;

}
