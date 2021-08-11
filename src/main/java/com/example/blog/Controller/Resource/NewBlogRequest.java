package com.example.blog.Controller.Resource;

import com.example.blog.Service.model.Visibility;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class NewBlogRequest {
    @NotNull
    @Size(min=2, max=30)
    private String text;

    private Visibility visibility;

    private Location location;
}
