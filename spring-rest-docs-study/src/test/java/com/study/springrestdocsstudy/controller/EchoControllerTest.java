package com.study.springrestdocsstudy.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class EchoControllerTest extends ControllerTestSupport {

    @DisplayName("spring rest docs 사용예시")
    @Test
    void echo() throws Exception {
        mockMvc.perform(
                post("/echo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(om.writeValueAsString(EchoRequest.of(EchoUser.of("홍길동"), "무야호")))
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.user.name").value("홍길동"))
                .andExpect(jsonPath("$.data.message").value("무야호"))
                .andDo(document("echo-post",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        requestFields(
                                subsectionWithPath("user").type(JsonFieldType.OBJECT).description("요청 에코 유저 정보"),
                                fieldWithPath("user.name").type(JsonFieldType.STRING) //field 설명
                                        .description("요청 에코 유저 이름"),
                                fieldWithPath("message").type(JsonFieldType.STRING)
                                        .description("요청 에코 메시지")
                        ),
                        responseFields(
                                beneathPath("data").withSubsectionId("data"),
                                subsectionWithPath("user").type(JsonFieldType.OBJECT)
                                        .description("요청 에코 유저 정보"),
                                fieldWithPath("user.name").type(JsonFieldType.STRING)
                                        .description("요청 에코 유저 이름"),
                                fieldWithPath("message").type(JsonFieldType.STRING)
                                        .description("요청 에코 메시지")
                        )
                ));
    }
}