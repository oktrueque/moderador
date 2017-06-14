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
    public String deleteCategory(@PathVariable int id){
         categoryService.deleteCategory(id);
         return "redirect:/categories";
    }


}
