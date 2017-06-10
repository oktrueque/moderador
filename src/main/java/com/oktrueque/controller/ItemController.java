package com.oktrueque.controller;

import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private ItemService itemService;

    @RequestMapping(method = RequestMethod.GET , value="/items")
    public String getItems(Model model){
        List<Item> items = itemService.getItems();
        model.addAttribute("items", items);
        return "items";
    }

//    @RequestMapping(method = RequestMethod.GET , value="/items") //Revisar este metodo con el team.
//    public String getItems(@RequestParam(value = "status") Integer status, Model model){
//        List<Item> items = itemService.getItemsByStatus(status);
//        model.addAttribute("items", items);
//        return "items";
//    }

    @RequestMapping(method = RequestMethod.GET, value="/items/{id}")
    public String getItemById(@PathVariable Long id, Model model){
        model.addAttribute("item" , itemService.getItemById(id));
        return "editItem";
    }












}
