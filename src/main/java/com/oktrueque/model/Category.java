package com.oktrueque.model;

import org.springframework.web.bind.annotation.ModelAttribute;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Fabrizio SPOSETTI on 01/05/2017.
 */

@Entity
@Table(name = "categories")
public class Category {

    @Id
    private int id;
    private String name;

//    @ModelAttribute("allCategories")
//    public List<Category> populateCategories() {
//
//
//
//        Category type1 = new Category();
//        type1.setId(1);
//        type1.setName("Muebles");
//
//        Category type2 = new Category();
//        type2.setId(2);
//        type2.setName("Electronicos");
//
//        List<Category> tipos = new ArrayList<>();
//        tipos.add(type1);
//        tipos.add(type2);
//        return tipos;
//    }

    public Category(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Category() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
