package com.oktrueque.controller;

import com.oktrueque.model.User;
import com.oktrueque.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import com.oktrueque.model.Item;
import com.oktrueque.service.ItemService;


/**
 * Created by Tomas on 21-May-17.
 */
@Controller
public class ItemController {

    @Autowired
    private ItemService itemService;
    @Autowired
    private CategoryService categoryService;
    private User user = new User();
    private List<Item> items = null;



    @RequestMapping(method = RequestMethod.GET , value="/items")
    public String getItems(Model model){
        List<Item> list = itemService.getItems();
        model.addAttribute("items", list);
    //    model.addAttribute("categories",categoryService.getCategories());
     //   model.addAttribute("item", new Item());
     //   user = list.get(0).getUser();
        return "items";
    }





}
