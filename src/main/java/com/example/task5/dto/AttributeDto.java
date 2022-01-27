package com.example.task5.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AttributeDto {
    private String value;

    public AttributeDto(String value) {
        this.value = value;
    }
}
