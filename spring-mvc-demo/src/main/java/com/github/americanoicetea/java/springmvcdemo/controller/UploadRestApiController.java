package com.github.americanoicetea.java.springmvcdemo.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.github.americanoicetea.java.springmvcdemo.service.FileData;
import com.github.americanoicetea.java.springmvcdemo.service.FilePoolService;

@RestController
public class UploadRestApiController {

    @Autowired
    private FilePoolService filePoolService;

    @PostMapping(value = "/upload/file/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> uploadFile(@PathVariable String id, @RequestParam MultipartFile file)
            throws IOException {
        Map<String, Object> meta = new HashMap<>();
        var fileData = new FileData(file.getContentType(), file.getSize(), file.getOriginalFilename(),
                file.getInputStream().readAllBytes());
        filePoolService.putFile(id, fileData);
        meta.put("name", file.getName());
        meta.put("originalName", file.getOriginalFilename());
        meta.put("contentType", file.getContentType());
        meta.put("contentLength", file.getSize());
        return ResponseEntity.ok(meta);
    }
}
