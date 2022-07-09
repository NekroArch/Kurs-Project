package com.example.common.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AttributeDto {
    private String attributeName;
    private String attributeType;

    public AttributeDto(String attributeName, String attributeType) {
        this.attributeName = attributeName;
        this.attributeType = attributeType;
    }
}
