package com.example.task5.service.impl;

import com.example.task5.entities.ItemAttribute;
import com.example.task5.repository.AttributeRepository;
import com.example.task5.repository.CollectionRepository;
import com.example.task5.service.ItemAttributeService;
import com.example.task5.service.UserService;

public class ItemAttributeImpl implements ItemAttributeService {

    private AttributeRepository attributeRepository;

    public ItemAttributeImpl(AttributeRepository attributeRepository) {
        this.attributeRepository = attributeRepository;
    }

    @Override
    public void save(int id, String value) {
//        attributeRepository.addAttributeValue("test", );
    }
}
