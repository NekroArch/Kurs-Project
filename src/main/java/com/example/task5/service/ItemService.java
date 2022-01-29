package com.example.task5.service;

import com.example.task5.dto.ItemAttributeDto;

import java.util.List;

public interface ItemService {

    void createItem(String name, int collectionId, List<String> tagName, List<ItemAttributeDto> itemAttribute);
}
