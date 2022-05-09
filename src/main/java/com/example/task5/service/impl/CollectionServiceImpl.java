package com.example.task5.service.impl;

import com.example.task5.dto.*;
import com.example.task5.entities.Attribute;
import com.example.task5.entities.Collection;
import com.example.task5.repository.AttributeRepository;
import com.example.task5.repository.CollectionRepository;
import com.example.task5.service.CollectionService;
import com.example.task5.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
public class CollectionServiceImpl implements CollectionService {

    private AttributeRepository attributeRepository;
    private CollectionRepository collectionRepository;
    private UserService userService;

    public CollectionServiceImpl(CollectionRepository collectionRepository, UserService userService, AttributeRepository attributeRepository) {
        this.collectionRepository = collectionRepository;
        this.userService = userService;
        this.attributeRepository = attributeRepository;
    }

    @Override
    public List<CollectionDto> getCollectionByUserId(int userId) {
        List<Collection> collections = collectionRepository.getCollectionByUserId(userId);
        return collections.stream()
                .map(x -> new CollectionDto(
                        x.getId(),
                        x.getName(),
                        x.getDescription(),
                        x.getTopic(),
                        x.getImageUrl(),
                        x.getItems().stream()
                                .map(z -> new ItemDto(z.getName(),
                                        z.getTags().stream()
                                                   .map(c -> new TagDto(c.getName()))
                                                .collect(toList()),
                                        z.getItemAttributes().stream()
                                                             .map(b -> new ItemAttributeServiceDto(b.getValue(), b.getAttribute().getId()))
                                                             .collect(Collectors.toList())))
                                .collect(toList()),
                        x.getAttributes().stream()
                                         .map(v -> new AttributeTypeDto(v.getName(), v.getType(), v.getId()))
                                         .collect(Collectors.toList())))
                .collect(toList());

    }

    @Transactional
    @Override
    public void deleteCollection(int id) {
        collectionRepository.deleteCollection(id);
    }

    @Override
    public int findCollectionIdByName(String name) {
        return collectionRepository.findCollectionIdByName(name);
    }

    @Override
    public void save(CreateCollectionDto collectionDto) {
        Collection collection = Collection.builder()
                .name(collectionDto.getName())
                .imageUrl(collectionDto.getImageUrl())
                .description(collectionDto.getDescription())
                .topic(collectionDto.getTopic())
                .user(userService.findUserByName(collectionDto.getUserName()))
                .build();
        collection.setAttributes(collectionDto.getAttributeContext().stream()
                .map(x -> new Attribute(x.getAttributeName(), collection))
                .collect(Collectors.toSet()));
        collectionRepository.save(collection);
    }

    public List<CollectionImgDto> getCollectionImg(){
        List<Collection> collections = collectionRepository.getAllCollection();
                                  ;
        return collections.stream()
                .map(x -> new CollectionImgDto(
                        x.getId(),
                        x.getImageUrl(),
                        x.getName(),
                        x.getItems().stream()
                                .map(z -> new ItemDto(z.getName(),
                                        z.getTags().stream()
                                                .map(c -> new TagDto(c.getName()))
                                                .collect(toList()),
                                        z.getItemAttributes().stream()
                                                .map(b -> new ItemAttributeServiceDto(b.getValue(), b.getAttribute().getId()))
                                                .collect(Collectors.toList())))
                                .collect(toList()),
                        x.getAttributes().stream()
                                .map(v -> new AttributeTypeDto(v.getName(), v.getType(), v.getId()))
                                .collect(Collectors.toList())))
                .collect(toList())
                .stream().sorted(Comparator.comparing(CollectionImgDto::getId).reversed())
                .toList()
                .stream().limit(10)
                .toList();

    }
}
