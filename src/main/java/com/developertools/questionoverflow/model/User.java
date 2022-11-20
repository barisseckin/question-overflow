package com.developertools.questionoverflow.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String mail;
    private String password;
    private boolean isActive = false;
    private String urlToImage;
    @OneToMany
    private Set<LikedQuestion> publicIdOfLikedQuestions;
    @OneToMany
    private Set<Link> links;

    public User(String username, String mail, String password, String urlToImage, Set<Link> links) {
        this.username = username;
        this.mail = mail;
        this.password = password;
        this.urlToImage = urlToImage;
        this.links = links;
    }
}
