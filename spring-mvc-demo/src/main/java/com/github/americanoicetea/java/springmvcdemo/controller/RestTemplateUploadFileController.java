package com.github.americanoicetea.java.springmvcdemo.controller;

import java.io.IOException;
import java.util.Map;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class RestTemplateUploadFileController {

    @PostMapping(value = "/resttemplate/upload/file", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Map<String, String> uploadFile(@RequestParam String id, @RequestParam MultipartFile file)
            throws IOException {
        RestTemplate rest = new RestTemplate();
        LinkedMultiValueMap<String, Object> partMap = new LinkedMultiValueMap<>();
        HttpHeaders partHeaders = new HttpHeaders();
        partHeaders.add("Content-Type", file.getContentType());
        partHeaders.add("Content-Disposition",
                "form-data; name=file; filename=" + file.getOriginalFilename());
        partMap.add("file", new HttpEntity<>(file.getBytes(), partHeaders));
        partMap.add("filename", file.getOriginalFilename());
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", MediaType.MULTIPART_FORM_DATA_VALUE);

        return rest.exchange("http://localhost:8081/springmvcdemo/upload/file/" + id, HttpMethod.POST,
                new HttpEntity<>(partMap, headers), new ParameterizedTypeReference<Map<String, String>>() {

                }).getBody();
    }

}
