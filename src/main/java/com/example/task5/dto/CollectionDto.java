package com.example.task5.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CollectionDto {
    private int id;
    private String name;
    private String description;
    private String topic;
    private List<ItemDto> items;
    private List<AttributeTypeDto> attributes;

    public CollectionDto(int id, String name, String description, String topic, List<ItemDto> items, List<AttributeTypeDto> attributes) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.topic = topic;
        this.items = items;
        this.attributes = attributes;
    }

    @Override
    public String toString() {
        return "CollectionDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", topic='" + topic + '\'' +
                ", items=" + items +
                '}';
    }
}
