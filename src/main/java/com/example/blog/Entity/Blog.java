package com.example.blog.Entity;

import com.example.blog.Service.model.Visibility;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;

    @CreationTimestamp
    private Date createdAt;

    @UpdateTimestamp
    private Date updatedAt;

    @Enumerated(EnumType.STRING)
    private Visibility visibility;

    private Location location;

    @Version
    private long version;

    public Blog(String text, Visibility visibility, Double lat, Double lon) {
        this.text = text;
        this.visibility = visibility;
        this.location = new Location(lat, lon);
    }

    @Embeddable
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    @ToString
    public static class Location {
        @Column(name = "location_lat")
        private Double lat;

        @Column(name = "location_lon")
        private Double lon;
    }
}
