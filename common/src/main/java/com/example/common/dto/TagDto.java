package com.example.common.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TagDto {

    private String name;

    public TagDto(String name) {
        this.name = name;
    }
}
