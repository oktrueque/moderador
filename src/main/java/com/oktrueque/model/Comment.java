package com.oktrueque.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String description;

    @ManyToOne
    @JoinColumn(name = "id_user_origin")
    private User userOrigin;

    @ManyToOne
    @JoinColumn(name = "id_user_target")
    private User userTarget;
    private int score;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;


    public Comment(String description, User user_target, User user_origin, int score, Date date) {
        this.description = description;
        this.userTarget = user_target;
        this.userOrigin = user_origin;
        this.score = score;
        this.date = date;
    }

    public Comment(){};

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUserTarget() {
        return userTarget;
    }

    public void setUserTarget(User userTarget) {
        this.userTarget = userTarget;
    }

    public User getUserOrigin() {
        return userOrigin;
    }

    public void setUserOrigin(User userOrigin) {
        this.userOrigin = userOrigin;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
