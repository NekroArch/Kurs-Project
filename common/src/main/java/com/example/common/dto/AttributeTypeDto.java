package com.example.common.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AttributeTypeDto {
    private String attributeName;
    private String attributeType;
    private int id;

    public AttributeTypeDto(String attributeName, String attributeType, int id) {
        this.attributeName = attributeName;
        this.attributeType = attributeType;
        this.id = id;
    }

    public AttributeTypeDto(String attributeName) {
        this.attributeName = attributeName;
    }
}
