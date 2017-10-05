package com.oktrueque.controller;

import com.oktrueque.model.Item;
import com.oktrueque.model.ItemTag;
import com.oktrueque.model.Tag;
import com.oktrueque.service.ItemService;
import com.oktrueque.service.ItemTagService;
import com.oktrueque.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.xml.ws.Response;
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

    @RequestMapping(method = RequestMethod.GET, value = "/tags/rest") //AutoComplete
    public ResponseEntity<List<Tag>> autocomplete(){
        List<Tag> tags = tagService.findAll();
        return new ResponseEntity<>(tags, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/tags")
    public String getTags(Model model){
        List<Tag> tags = tagService.findAll();
        model.addAttribute("tags", tags);
        return "tags";
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
    public ResponseEntity<Boolean> deleteTag(@PathVariable Long id){
        List<ItemTag> itemTags = itemTagService.getItemTagByTagId(id);
        if(itemTags.size()>0){
            return new ResponseEntity<>(false, HttpStatus.OK);
        }else{
            tagService.deleteTag(id);
            return new ResponseEntity<>(true, HttpStatus.OK);
        }
    }

}
