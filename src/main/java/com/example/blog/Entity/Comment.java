package com.example.blog.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;

    private Long timestamp;

    @ManyToOne
    @JoinColumn(name="blog_id")
    private Blog blog;

    public Comment(String text, Long timestamp, Blog blog) {
        this.text = text;
        this.timestamp = timestamp;
        this.blog = blog;
    }
}
