package com.oktrueque.model;

import javax.persistence.*;

/**
 * Created by OkTrueque on 7/5/2017.
 */
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "status")
    private Integer status;
    @Column(name = "photo1")
    private String photo1;
    @Column(name = "items_amount")
    private Integer itemsAmount;
    @Column(name = "username")
    private String username;
    @Column(name = "score")
    private Integer score;


    public User() {
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoto1() {
        return photo1;
    }

    public void setPhoto1(String photo1) {
        this.photo1 = photo1;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getItemsAmount() {
        return itemsAmount;
    }

    public void setItemsAmount(Integer itemsAmount) {
        this.itemsAmount = itemsAmount;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}
