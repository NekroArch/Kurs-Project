package com.example.common.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemAttributeServiceDto {
    private String value;
    private int attribute_id;

    public ItemAttributeServiceDto(String value, int attribute_id) {
        this.value = value;
        this.attribute_id = attribute_id;
    }
}
