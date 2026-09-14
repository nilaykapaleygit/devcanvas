package com.devcanvas.backend.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OpenAiRequest {

    private String model;

    private List<Input> input;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Input {

        private String role;

        private List<Content> content;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Content {

        private String type;

        private String text;
    }
}
