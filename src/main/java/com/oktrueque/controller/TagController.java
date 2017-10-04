package com.oktrueque.controller;

import com.oktrueque.model.Item;
import com.oktrueque.model.Tag;
import com.oktrueque.service.ItemService;
import com.oktrueque.service.ItemTagService;
import com.oktrueque.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@Controller
public class TagController {

    private TagService tagService;
    private ItemService itemService;
    private ItemTagService itemTagService;

    @Autowired
    public TagController(TagService tagService, ItemService itemService, ItemTagService itemTagService) {
        this.tagService = tagService;
        this.itemService = itemService;
        this.itemTagService = itemTagService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/tags") //AutoComplete
    public ResponseEntity<List<Tag>> autocomplete(){
        List<Tag> tags = tagService.findAll();
        return new ResponseEntity<>(tags, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/items/{id}/updateItemTags")
    @Transactional
    public ResponseEntity<Void> updateItemTags(@RequestBody List<Tag> tags, @PathVariable Long id){
        Item item = itemService.getItemById(id);
        itemTagService.deleteAllByItemId(item.getId());
        itemTagService.saveItemTags(item.getId(),tags);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.POST,value = "/tags")
    public String addTag(@ModelAttribute Tag tag){
        tagService.addTag(tag);
        return "redirect:/tags";
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/tags/{id}")
    public String updateTag(@ModelAttribute Tag tag){
        tagService.updateTag(tag);
        return "redirect:/tags";
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "tags/{id}")
    public String deleteTag(@PathVariable Long id){
        tagService.deleteTag(id);
        return "redirect:/tags";
    }

}
