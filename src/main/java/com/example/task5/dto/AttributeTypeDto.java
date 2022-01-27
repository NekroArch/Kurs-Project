package com.example.task5.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AttributeTypeDto {
    private String attributeName;
    private String attributeType;

    public AttributeTypeDto(String attributeName, String attributeType) {
        this.attributeName = attributeName;
        this.attributeType = attributeType;
    }
}
