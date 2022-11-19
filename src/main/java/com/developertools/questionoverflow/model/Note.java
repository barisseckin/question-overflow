package com.developertools.questionoverflow.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String description;
    private String publicId = UUID.randomUUID().toString();
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Link> urlToImages;
    @ManyToOne
    private User user;

    public Note(String title, String description, List<Link> urlToImages, User user) {
        this.title = title;
        this.description = description;
        this.urlToImages = urlToImages;
        this.user = user;
    }
}
