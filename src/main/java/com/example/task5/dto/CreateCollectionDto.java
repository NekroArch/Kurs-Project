package com.example.task5.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CreateCollectionDto {
    private String name;
    private String description;
    private String topic;
    private String userName;
    private List<AttributeTypeDto> attributeContext;

}
