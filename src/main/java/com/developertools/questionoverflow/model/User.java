package com.developertools.questionoverflow.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

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
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<LikedQuestion> publicIdOfLikedQuestions;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Link> links;
    private boolean notificationPermission = true;
    private int totalReportNumber = 0;

    public User(String username, String mail, String password, String urlToImage, List<Link> links) {
        this.username = username;
        this.mail = mail;
        this.password = password;
        this.urlToImage = urlToImage;
        this.links = links;
    }
}
