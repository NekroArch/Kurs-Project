package com.example.common.service;

import java.util.List;

public interface ItemService {

    void createItem(String name, int collectionId, String tagName, List<String> ItemAttributeNameDto, List<String> itemAttributeValueDto);
}
