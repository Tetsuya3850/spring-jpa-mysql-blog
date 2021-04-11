package com.example.blog.Controller.Resource;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class BlogRequest {
    @NotNull
    @Size(min=2, max=30)
    private String text;

    private String visibility;

    private Location location;

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    public static class Location {
        private Double lat;
        private Double lon;
    }
}
