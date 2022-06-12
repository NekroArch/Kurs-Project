package com.example.task5.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class CreateCollectionDto {
    private String name;
    private String imageUrl;
    private String description;
    private String topic;
    private String userName;
    private List<AttributeTypeDto> attributeContext;

}
