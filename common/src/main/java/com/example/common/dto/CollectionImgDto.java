package com.example.common.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CollectionImgDto {
    private int id;
    private String name;
    private String imageUrl;
    private List<ItemDto> items;
    private List<AttributeTypeDto> attributes;


    public CollectionImgDto(int id, String imageUrl, String name) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.name = name;
    }

    public CollectionImgDto(int id, String imageUrl, String name, List<ItemDto> items, List<AttributeTypeDto> attributes) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.name = name;
        this.items = items;
        this.attributes = attributes;
    }

    @Override
    public String toString() {
        return "CollectionImgDto{" +
                ", id='" + id +
                ", imageUrl='" + imageUrl +
                ", name='" + name +
                '}';
    }

}


