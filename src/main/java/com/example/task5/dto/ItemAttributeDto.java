package com.example.task5.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemAttributeDto {
    private String value;

    public ItemAttributeDto(String value) {
        this.value = value;
    }
}
