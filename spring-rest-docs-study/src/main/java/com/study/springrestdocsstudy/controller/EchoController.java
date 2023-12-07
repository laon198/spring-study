package com.study.springrestdocsstudy.controller;

import lombok.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.beans.ConstructorProperties;

@RestController
public class EchoController {
    @PostMapping("/echo")
    public ApiResponse echo(@RequestBody EchoRequest request) {
        return ApiResponse.of(EchoResponse.of(request.getUser(), request.getMessage()));
    }
}

@Data
@AllArgsConstructor
class ApiResponse<T>{
    private T data;

    public static <T> ApiResponse<T> of(T data) {
        return new ApiResponse<>(data);
    }
}

@NoArgsConstructor
@Data
@AllArgsConstructor
class EchoRequest {
    private EchoUser user;
    private String message;
    public static EchoRequest of(EchoUser user, String message) {
        return new EchoRequest(user, message);
    }
}

@AllArgsConstructor
@NoArgsConstructor
@Data
class EchoUser{
    private String name;

    public static EchoUser of(String name) {
        return new EchoUser(name);
    }
}


@Data
@NoArgsConstructor
@AllArgsConstructor
class EchoResponse {
    private EchoUser user;
    private String message;
    public static EchoResponse of(EchoUser user, String message) {
        return new EchoResponse(user, message);
    }
}
