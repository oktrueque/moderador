package com.oktrueque.controller;

import com.oktrueque.model.Tag;
import com.oktrueque.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Facundo on 9/13/2017.
 */
@Controller
public class TagController {

    @Autowired
    private TagService tagService;

    @RequestMapping(method = RequestMethod.GET, value = "/tags")
    public String getAllTags(Model model){
        model.addAttribute("tags" , tagService.getAllTags());
        return "tags";
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
