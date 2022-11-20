package com.developertools.questionoverflow.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
public class Question extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String description;
    private boolean isDone = false;
    private String publicId = UUID.randomUUID().toString();
    private int likedNumber = 0;
    @OneToMany
    @ToString.Exclude
    private List<Link> urlToImages;
    @OneToMany
    @ToString.Exclude
    private Set<Link> tags;
    @OneToOne
    private Category category;
    @OneToOne
    private User user;

    public Question(String title, String description, List<Link> urlToImages, Set<Link> tags,
                    Category category, User user) {
        this.title = title;
        this.description = description;
        this.urlToImages = urlToImages;
        this.tags = tags;
        this.category = category;
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Question question = (Question) o;
        return id != null && Objects.equals(id, question.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
