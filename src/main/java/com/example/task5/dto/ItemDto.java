package com.example.task5.dto;

import com.example.task5.entities.Attribute;
import com.example.task5.entities.Item;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
public class ItemDto {

    private String name;
    private int collectionId;
    private List<TagDto> tags;
    private List<AttributeDto> attributes;

    public ItemDto(String name, List<TagDto> tags) {
        this.name = name;
        this.tags = tags;
    }

    public ItemDto(String name, List<TagDto> tags, List<AttributeDto> attributes) {
        this.name = name;
        this.tags = tags;
        this.attributes = attributes;
    }

    @Override
    public String toString() {
        return "ItemDto{" +
                "name=" + name +
                ", collectionId=" + collectionId +
                '}';
    }
}
