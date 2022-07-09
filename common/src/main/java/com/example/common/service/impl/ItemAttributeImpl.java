package com.example.common.service.impl;

import com.example.entities.ItemAttribute;
import com.example.common.repository.AttributeRepository;
import com.example.common.service.ItemAttributeService;

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
