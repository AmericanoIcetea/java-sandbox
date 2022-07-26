package com.github.americanoicetea.java.springmvcdemo.controller;

import java.io.IOException;
import java.util.Map;

import org.apache.catalina.connector.Response;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class RestTemplateDownalodFileController {

    @GetMapping(value = "/resttemplate/download/file/{id}", produces = MediaType.ALL_VALUE)
    public ResponseEntity<Object> downloadFile(@PathVariable String id)
            throws IOException {
        RestTemplate rest = new RestTemplate();
        var response = rest.exchange("http://localhost:8081/springmvcdemo/download/file/" + id, HttpMethod.GET, null,
                byte[].class);
        var header = new HttpHeaders();
        byte[] body = response.getBody();
        System.out.println(body.length);
        System.out.println(response.getHeaders().getContentType());
        System.out.println(response.getHeaders().getContentDisposition());
        header.setContentType(response.getHeaders().getContentType());
        header.setContentDisposition(response.getHeaders().getContentDisposition());

        return new ResponseEntity<>(body, header, HttpStatus.OK);
    }
}
