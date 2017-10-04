package com.oktrueque.controller;

import com.oktrueque.model.Category;
import com.oktrueque.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.oktrueque.model.Item;
import com.oktrueque.service.ItemService;


/**
 * Created by Tomas on 21-May-17.
 */

@Controller
public class ItemController {

    private ItemService itemService;
    private CategoryService categoryService;

    @Autowired
    public ItemController(ItemService itemService, CategoryService categoryService){
        this.itemService = itemService;
        this.categoryService = categoryService;
    }

    @RequestMapping(method = RequestMethod.GET , value="/items")
    public String getItems(@RequestParam(required = false) Integer status, Model model){
        if(status == null) status = 0;
        if(status == 0){
            model.addAttribute("items", itemService.findItemsByStatus(status));
            return "itemsPending";
        }
        model.addAttribute("items", itemService.findItemsByStatus(status));
        return "items";
    }

    @RequestMapping(method = RequestMethod.GET, value="/items/{id}")
    public String getItemById(@PathVariable Long id, Model model){
        model.addAttribute("item" , itemService.getItemById(id));
        model.addAttribute("categories", categoryService.getAllCategories());
        return "editItem";
    }

    @RequestMapping(method= RequestMethod.PUT, value="/items/{id}")
    public String setItem(@ModelAttribute Item item, @PathVariable Long id, Model model) {
        model.addAttribute("item", itemService.setItem(item));
        return "redirect:/items/" + id;
    }

    @RequestMapping(method= RequestMethod.DELETE, value="/items/{id}")
    public String deleteItem(@PathVariable Long id, Model model){
        itemService.deleteItemAlone(id);
        return "redirect:/items";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/items/{id}/approve")
    public ResponseEntity approveItem(@PathVariable Long id){
        itemService.approveItem(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
