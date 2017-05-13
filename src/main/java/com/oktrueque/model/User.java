package com.oktrueque.model;

import javax.persistence.*;

/**
 * Created by Felipe on 7/5/2017.
 */
@Entity
@Table(name = "users")
public class User {

    @Id
    private Long id;
    private String name;
    private String last_name;
    private String email;
    private String password;
    private Integer status;
    private String photo1;


    public User() {
    }

    public User(Long id, String name, String last_name, String email, String password, int status, String photo1) {
        this.id = id;
        this.name = name;
        this.last_name = last_name;
        this.email = email;
        this.password = password;
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

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
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
}
