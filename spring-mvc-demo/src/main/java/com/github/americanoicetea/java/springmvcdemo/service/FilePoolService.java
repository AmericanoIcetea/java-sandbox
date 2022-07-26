package com.github.americanoicetea.java.springmvcdemo.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class FilePoolService {

    private final Map<String, FileData> FILE_MAP_COLLECTION = new HashMap<>();

    public void putFile(String id, FileData fileData) {
        FILE_MAP_COLLECTION.put(id, fileData);
    }

    public FileData getFile(String id) {
        return FILE_MAP_COLLECTION.get(id);
    }
}
