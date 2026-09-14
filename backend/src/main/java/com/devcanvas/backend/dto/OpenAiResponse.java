package com.devcanvas.backend.dto;

import java.util.List;

import lombok.Data;

@Data
public class OpenAiResponse {

    private String id;

    private String object;

    private List<Output> output;

    @Data
    public static class Output {

        private String type;

        private String role;

        private List<Content> content;
    }

    @Data
    public static class Content {

        private String type;

        private String text;
    }
}