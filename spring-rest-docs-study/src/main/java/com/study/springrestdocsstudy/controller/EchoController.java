package com.study.springrestdocsstudy.controller;

import lombok.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EchoController {
    @PostMapping("/echo")
    public EchoResponse echo(@RequestBody EchoRequest request) {
        return EchoResponse.of(request.getMessage());
    }
}

@Data
class EchoRequest {
    private String message;
    public static EchoRequest of(String message) {
        EchoRequest request = new EchoRequest();
        request.setMessage(message);
        return request;
    }
}

@Data
class EchoResponse {
    private String message;
    public static EchoResponse of(String message) {
        EchoResponse response = new EchoResponse();
        response.setMessage(message);
        return response;
    }
}
