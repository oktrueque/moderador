package com.oktrueque.controller;

import com.oktrueque.model.Category;
import com.oktrueque.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class CategoryController {


    @Autowired
    private CategoryService categoryService;

    @RequestMapping(method = RequestMethod.GET, value = "/categories")
    public String getAllCategories(Model model){
        model.addAttribute("categories" , categoryService.getAllCategories());
        return "categories";
    }

    @RequestMapping(method = RequestMethod.POST,value = "/categories")
    public String addCategory(@ModelAttribute Category category){
        categoryService.addCategory(category);
        return "redirect:/categories";
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/categories/{id}")
    public String updateCategory(@ModelAttribute Category category){
        categoryService.updateCategory(category);
        return "redirect:/categories";
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "categories/{id}")
    public ResponseEntity<Boolean> deleteCategory(@PathVariable int id){
         try{
             categoryService.deleteCategory(id);
         }catch(Exception e){
             return new ResponseEntity<>(false, HttpStatus.OK);
         }
         return new ResponseEntity<>(true, HttpStatus.OK);
    }


}

