package com.example.common.service;

import com.example.common.dto.CollectionDto;
import com.example.common.dto.CollectionImgDto;
import com.example.common.dto.create.CreateCollectionDto;

import java.util.List;

public interface CollectionService {

    List<CollectionDto> getCollectionByUserId(int userId);

    void deleteCollection(int id);

    int findCollectionIdByName(String name);

    void save(CreateCollectionDto collectionDto);

    List<CollectionImgDto> getCollectionImg();

    List<CollectionDto> findCollectionByName(String collectionName);
}
