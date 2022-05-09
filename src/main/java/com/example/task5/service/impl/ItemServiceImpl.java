package com.example.task5.service.impl;

import com.example.task5.repository.AttributeRepository;
import com.example.task5.repository.ItemRepository;
import com.example.task5.repository.TagRepository;
import com.example.task5.service.ItemService;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    private ItemRepository itemRepository;
    private TagRepository tagRepository;
    private AttributeRepository attributeRepository;

    public ItemServiceImpl(ItemRepository itemRepository, TagRepository tagRepository, AttributeRepository attributeRepository) {
        this.itemRepository = itemRepository;
        this.tagRepository = tagRepository;
        this.attributeRepository = attributeRepository;
    }

    @Transactional
    @Override
    public void createItem(String name, int collectionId, String tagName, List<String> itemAttributeNameDto, List<String> itemAttributeValueDto) {
        itemRepository.createItem(name, collectionId);
        tagRepository.createTag(tagName);
        tagRepository.setTagItem(itemRepository.findItemIdByName(name),  tagRepository.findTagIdByName(tagName));
        for(int i = 0; i < itemAttributeValueDto.size(); i++){
            if(itemAttributeNameDto.get(i) != null && itemAttributeValueDto.get(i) != null) {
                attributeRepository.addAttributeValue(itemAttributeValueDto.get(i), itemRepository.findItemIdByName(name), attributeRepository.findAttributeIdByName(itemAttributeNameDto.get(i)));
            }
        }
    }
}
