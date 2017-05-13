package com.oktrueque.service;

import com.oktrueque.model.Category;
import com.oktrueque.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Fabrizio SPOSETTI on 01/05/2017.
 */

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository CategoryRepository;


    public List<Category> getAllCategories(){
          
        List<Category> categories = new ArrayList<>();
        CategoryRepository.findAll()
        .forEach(categories::add);
        return categories;
          
    }


    //Agregar una categoria
    public void addCategory(Category Category){
        CategoryRepository.save(Category);
    }


    //Obtener una categoria en particular por su id
    public Category getCategory(int id){

      return CategoryRepository.findOne(id);
    }


    //Editar una categoria
    public void updateCategory(Category category){

        CategoryRepository.save(category);

    }


    //Eliminar una categoria
    public void deleteCategory(int id){

        CategoryRepository.delete(id);
    }



}
