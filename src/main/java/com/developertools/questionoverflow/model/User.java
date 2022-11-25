package com.developertools.questionoverflow.model;

import com.developertools.questionoverflow.model.enums.UserRole;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@ToString
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
    @ToString.Exclude
    private List<LikedQuestion> publicIdOfLikedQuestions;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Link> links;
    private boolean notificationPermission = true;
    private int totalReportNumber = 0;
    private LocalDateTime lastLoginDate;
    @Enumerated(EnumType.STRING)
    private UserRole role;

    public User(String username, String mail, String password, String urlToImage,
                List<Link> links, UserRole role) {
        this.username = username;
        this.mail = mail;
        this.password = password;
        this.urlToImage = urlToImage;
        this.links = links;
        this.role = role;
    }
}
