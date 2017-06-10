package com.oktrueque.controller;

import com.oktrueque.model.Category;
import com.oktrueque.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by Fabrizio SPOSETTI on 01/05/2017.
 */

@Controller
public class CategoryController {


    @Autowired
    private CategoryService categoryService;

    @RequestMapping("/categories")
    public String getAllCategories(Model model){
        model.addAttribute("category" , new Category());
        model.addAttribute("categories" , categoryService.getAllCategories());
        return "categories";
    }

    @RequestMapping(method = RequestMethod.POST,value = "/categories")
    public String addCategory(@ModelAttribute Category category,Model model){
        categoryService.addCategory(category);
        return "redirect:/categories";
    }

    // Obtengo la categoria para poder upgradiarla
    @RequestMapping("/categories/edit{id}")
    public String getCategory(@PathVariable int id, Model model){
            model.addAttribute("category" , categoryService.getCategory(id));
            return "updateCategory";

    }

    @RequestMapping(method = RequestMethod.PUT, value = "/updateCategory")
    public String updateCategory(@ModelAttribute Category category,Model model){
        categoryService.updateCategory(category);
        return "redirect:/categories";
    }



    @RequestMapping("/categories/del{id}")
    public String deleteCategory(@PathVariable int id){
         categoryService.deleteCategory(id);
         return "redirect:/categories";
    }

    @RequestMapping("/items/{id}") //Esto se usa para la lista desplegable del FORM de edit Items
    public String getAllCategoriesForList(Model model){
        model.addAttribute("categories" , categoryService.getAllCategories());
        return "editItem";
    }
}
