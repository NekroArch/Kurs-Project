package com.example.task5.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CreateItemDto {

    private String name;
    private int collectionId;
    private String tagName;
    private List<String> itemAttributeNameDto;
    private List<String> itemAttributeValueDto;


    @Override
    public String toString() {
        return "ItemDto{" +
                "name=" + name +
                ", collectionId=" + collectionId +
                '}';
    }
}
