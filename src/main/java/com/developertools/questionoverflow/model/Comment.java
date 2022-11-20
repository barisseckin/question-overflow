package com.developertools.questionoverflow.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
public class Comment extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String body;
    private int likedNumber = 0;
    private String publicId = UUID.randomUUID().toString();
    private boolean approved = false;
    @OneToMany
    @ToString.Exclude
    private List<Link> urlToImages;
    @OneToOne
    private Question question;
    @OneToOne
    private User user;

    public Comment(String body, List<Link> urlToImages, Question question, User user) {
        this.body = body;
        this.urlToImages = urlToImages;
        this.question = question;
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Comment comment = (Comment) o;
        return id != null && Objects.equals(id, comment.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
