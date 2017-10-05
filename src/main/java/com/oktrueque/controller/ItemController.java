package com.oktrueque.controller;

import com.oktrueque.model.Category;
import com.oktrueque.model.Tag;
import com.oktrueque.service.AwsS3Service;
import com.oktrueque.service.CategoryService;
import com.oktrueque.service.ItemTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import com.oktrueque.model.Item;
import com.oktrueque.service.ItemService;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class ItemController {

    private ItemService itemService;
    private CategoryService categoryService;
    private ItemTagService itemTagService;
    private AwsS3Service awsS3Service;
    @Value("${aws.s3.fileName.items}")
    private String fileNameItems;

    @Autowired
    public ItemController(ItemService itemService, CategoryService categoryService, ItemTagService itemTagService, AwsS3Service awsS3Service){
        this.itemService = itemService;
        this.categoryService = categoryService;
        this.itemTagService = itemTagService;
        this.awsS3Service = awsS3Service;
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
        Item item = itemService.getItemById(id);
        List<Tag> tags = itemTagService.getItemTags(id);
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        model.addAttribute("item", item);
        model.addAttribute("hasTags", tags.size() != 0 ? true : false);
        model.addAttribute("tags", tags);
        model.addAttribute("photo1", item.getPhoto1());
        model.addAttribute("photo2", item.getPhoto2());
        model.addAttribute("photo3", item.getPhoto3());
        return "updateItem";
    }

    @RequestMapping(method = RequestMethod.PUT, value="/items/{id}")
    public String updateItemById(@ModelAttribute Item item, @ModelAttribute List<MultipartFile> pictures, Principal principal){
        if(!pictures.get(0).getOriginalFilename().equals("")){
            String pictureUrl = awsS3Service.uploadFileToS3(pictures.get(0), fileNameItems, item.getId(), "1", item.getPhoto1());
            item.setPhoto1(pictureUrl);
        }
        if(!pictures.get(1).getOriginalFilename().equals("")){
            String pictureUrl = awsS3Service.uploadFileToS3(pictures.get(1), fileNameItems, item.getId(), "2", item.getPhoto1());
            item.setPhoto2(pictureUrl);
        }
        if(!pictures.get(2).getOriginalFilename().equals("")){
            String pictureUrl = awsS3Service.uploadFileToS3(pictures.get(2), fileNameItems, item.getId(), "3", item.getPhoto1());
            item.setPhoto3(pictureUrl);
        }
        Item itemSaved = itemService.getItemById(item.getId());
        item.setTags(itemSaved.getTags());
        itemService.saveItem(item);
        return "redirect:/items";
    }

    @RequestMapping(method= RequestMethod.DELETE, value="/items/{id}")
    public ResponseEntity deleteItem(@PathVariable Long id){
        itemService.deleteItemAlone(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/items/{id}/approve")
    public ResponseEntity approveItem(@PathVariable Long id){
        itemService.approveItem(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
