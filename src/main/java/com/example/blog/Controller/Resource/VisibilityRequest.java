package com.example.blog.Controller.Resource;

import com.example.blog.Service.model.Visibility;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class VisibilityRequest {
    @NotNull
    private Visibility visibility;
}
