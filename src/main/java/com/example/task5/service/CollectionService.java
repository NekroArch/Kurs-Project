package com.example.task5.service;

import com.example.task5.dto.CollectionDto;
import com.example.task5.dto.CollectionImgDto;
import com.example.task5.dto.CreateCollectionDto;

import java.util.List;

public interface CollectionService {

    List<CollectionDto> getCollectionByUserId(int userId);

    void deleteCollection(int id);

    int findCollectionIdByName(String name);

    void save(CreateCollectionDto collectionDto);

    List<CollectionImgDto> getCollectionImg();
}
