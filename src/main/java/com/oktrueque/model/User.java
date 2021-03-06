package com.oktrueque.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Entity
@Table(name = "users")
public class User implements UserDetails{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "lastName")
    private String lastName;
    @Column(name = "email")
    @NotNull
    @NotEmpty
    private String email;
    @Column(name = "password")
    @NotNull
    @NotEmpty
    private String password;
    @Column(name = "status")
    private Integer status;
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Item> items;
    @Column(name = "items_amount")
    private Integer itemsAmount;
    @Column(name = "photo1")
    private String photo1;
    @Column(name = "username")
    @NotNull
    @NotEmpty
    private String username;
    @OneToMany(mappedBy = "userTarget")
    @JsonIgnore
    private List<Comment> comments;
    @Column(name = "score")
    private Integer score;
    @Column(name="wallpaper")
    private String wallpaper;
    @OneToMany(mappedBy = "userTarget")
    @JsonIgnore
    private List<Complaint> complaints;
    @Column(name = "register_date")
    private Date registerDate;


    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY  )
    @JoinTable( name = "users_tags",
            joinColumns = {@JoinColumn(name="id_user")},
            inverseJoinColumns = {@JoinColumn(name="id_tag")})
    private List<Tag> tags = new ArrayList<>();

    public List<Complaint> getComplaints() {
        return complaints;
    }

    public void setComplaints(List<Complaint> complaints) {
        this.complaints = complaints;
    }

    public User() {
    }

    public User(String name, String lastName, String email, String password, Integer status, List<Item> items, Integer itemsAmount, String photo1, String username, List<Comment> comments, Integer score, String wallpaper, List<Complaint> complaints, Date registerDate, List<Tag> tags) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.status = status;
        this.items = items;
        this.itemsAmount = itemsAmount;
        this.photo1 = photo1;
        this.username = username;
        this.comments = comments;
        this.score = score;
        this.wallpaper = wallpaper;
        this.complaints = complaints;
        this.registerDate = registerDate;
        this.tags = tags;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public String getWallpaper() {
        return wallpaper;
    }

    public void setWallpaper(String wallpaper) {
        this.wallpaper = wallpaper;
    }

    public String getPhoto1() {
        return photo1;
    }

    public void setPhoto1(String photo1) {
        this.photo1 = photo1;
    }

    public Integer getItemsAmount() {
        return itemsAmount;
    }

    public void setItemsAmount(Integer itemsAmount) {
        this.itemsAmount = itemsAmount;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getFullName(){
        return name + " " + lastName;
    }

    public Boolean checkPassword(String password){
        return this.password.equals(password);
    }

    public Boolean checkUsername(){
        Pattern p = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(this.username);
        if(this.username.length() < 1 || m.find() || this.username.split(" ").length > 1) return false;
        return true;
    }

    public Boolean checkEmail(){
        Pattern p = Pattern.compile("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])");
        Matcher m = p.matcher(this.email);
        if(m.find()) return true;
        return false;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    private String getWallpaperPath(){
        Integer number = (int) (Math.random() * 10) + 1;
        return "./static/common/img/temp/photos/"+ number.toString() + ".jpeg";
    }
}