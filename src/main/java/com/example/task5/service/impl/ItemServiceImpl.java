package com.example.task5.service.impl;

import com.example.task5.dto.ItemAttributeDto;
import com.example.task5.repository.AttributeRepository;
import com.example.task5.repository.ItemRepository;
import com.example.task5.repository.TagRepository;
import com.example.task5.service.ItemService;
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
    public void createItem(String name, int collectionId, List<String> tagName, List<ItemAttributeDto> itemAttribute) {
        itemRepository.createItem(name, collectionId);
        tagName.forEach(x->tagRepository.createTag(x));
        tagName.forEach(x->
                tagRepository.setTagItem(
                        itemRepository.findItemIdByName(name),
                        tagRepository.findTagIdByName(x))
                );
    }
}
