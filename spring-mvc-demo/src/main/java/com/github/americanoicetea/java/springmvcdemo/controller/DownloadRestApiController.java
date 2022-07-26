package com.github.americanoicetea.java.springmvcdemo.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.github.americanoicetea.java.springmvcdemo.service.FilePoolService;

@RestController
public class DownloadRestApiController {
    @Autowired
    private FilePoolService filePoolService;

    @GetMapping(value = "/download/file/{id}", produces = MediaType.ALL_VALUE)
    public ResponseEntity<Object> downloadFile(@PathVariable String id)
            throws IOException {
        var fileData = filePoolService.getFile(id);
        ResponseEntity<Object> response = null;
        if (fileData != null) {
            MultiValueMap<String, String> header = new LinkedMultiValueMap<>();
            header.add("Content-Type", fileData.getContentType());
            System.out.println(fileData.getContentType());
            header.add("Content-Disposition", "attachment; filename=" + fileData.getName());
            response = new ResponseEntity<>(fileData.getData(), header, HttpStatus.OK);
        } else {
            response = ResponseEntity.noContent().build();
        }
        return response;
    }

}
