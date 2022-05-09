package com.example.task5.service;

import java.util.List;

public interface ItemService {

    void createItem(String name, int collectionId, String tagName, List<String> ItemAttributeNameDto, List<String> itemAttributeValueDto);
}
