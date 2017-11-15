package com.oktrueque.controller;

import com.oktrueque.model.*;
import com.oktrueque.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.LinkedList;
import java.util.List;

@Controller
public class UserController {

    private UserService userService;
    private ItemServiceImpl itemService;
    private UserTagService userTagService;
    private ComplaintService complaintService;
    private TruequeService truequeService;

    @Autowired
    public UserController(UserService userService, ItemServiceImpl itemService, UserTagService userTagService, ComplaintService complaintService, TruequeService truequeService){
        this.userService = userService;
        this.itemService = itemService;
        this.userTagService = userTagService;
        this.complaintService = complaintService;
        this.truequeService = truequeService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/users")
    public String getUsers(@RequestParam(required = false) Integer status, Model model){
        if(status == null) status = 0;
        model.addAttribute("users", userService.findUsersByStatus(status));
        return "users";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/users")
    public String addUser(@ModelAttribute User user, @RequestParam("image") MultipartFile image){
        if(!userService.checkIfUserExists(user.getEmail(), user.getUsername())){
            userService.addUser(user, image);
        }
        return "redirect:/users";
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/users/{id}")
    public String updateUser(@ModelAttribute User user){
        userService.updateUser(user);
        return "redirect:/users/" + user.getUsername();
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/users/{id}")
    public ResponseEntity<Boolean> deleteUser(@PathVariable Long id){
        try{
            userService.deleteUser(id);
        }catch(Exception e){
            return new ResponseEntity<>(false, HttpStatus.OK);
        }
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @RequestMapping("/login")
    public String loginUser() {
        return "login";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/users/{username}")
    public String getUserProfile(Model model, @PathVariable String username, @PageableDefault(value = 2) Pageable pageable) {
        User user = userService.getUserByUsername(username);
        List<Item> items = itemService.getItemsByUserUsername(user.getUsername(), pageable);
        List<UserTag> tags = userTagService.getUserTagByUserId(user.getId());
        List<Comment> comments = user.getComments();
        List<Complaint> complaints = complaintService.getComplaintsByUserTarget(user.getId());
        List<User> usersComplainers = userService.findUsersByIds(complaints);
        List<UserTrueque> userTrueques= truequeService.getUserTruequeById_UserId(user.getId());

        Trueque TruequeNuevo;
        LinkedList<Trueque> trueques = new LinkedList<>();
        for (UserTrueque trueque: userTrueques){
            TruequeNuevo = truequeService.getTruequeById(trueque.getId().getTrueque().getId());
            trueques.add(TruequeNuevo);
        }
        model.addAttribute("user", user);
        model.addAttribute("hasItems", items.size() != 0 ? true : false);
        model.addAttribute("items", items);
        model.addAttribute("hasTrueques", trueques.size() != 0 ? true : false);
        model.addAttribute("trueques", trueques);
        model.addAttribute("hasComments", comments.size() != 0 ? true : false);
        model.addAttribute("comments", comments);
        model.addAttribute("hasTags", tags.size() != 0 ? true : false);
        model.addAttribute("tags", tags);
        model.addAttribute("hasComplaints", complaints.size() != 0 ? true : false);
        model.addAttribute("complaints", complaints);

        return "user";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/users/mensaje/{username}")
    public String sendMail(@PathVariable String username, @RequestParam(value = "subject") String subject, @RequestParam(value = "text") String text) {

            User userTarget = userService.getUserByUsername(username);
            userService.sendEmailToUser(userTarget, subject, text);

        return "redirect:/users/" + username ;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/user/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        if(user != null){
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
