package com.oktrueque.controller;

import com.oktrueque.model.Item;
import com.oktrueque.model.User;
import com.oktrueque.service.RedService;
import com.oktrueque.service.TruequeService;
import org.omg.PortableInterceptor.USER_EXCEPTION;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Created by Facundo on 9/19/2017.
 */
@Controller
public class RedController {

    @Autowired
    private RedService redService;
    @Autowired
    private TruequeService truequeService;

    @RequestMapping(method = RequestMethod.GET, value = "/propuesta/inicio")
    public String getUserWithTagsAndItemsWithTags(Model model){
        model.addAttribute("usersWithTags", redService.getAllUsersWithTags());
        return "propuesta";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/propuesta/paso2")
    public ResponseEntity<Map<String,Object>> getUserLevel2(@RequestBody User user){
        Map<String, Object> map = new LinkedHashMap<>();
        List<User> users = redService.getUsersByPreferences(user.getId());
        if(users.size() > 0){
            map.put("users", users);
            return new ResponseEntity<>(map, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @RequestMapping(method = RequestMethod.POST, value = "/propuesta/paso3/{idUserInitial}")
    public ResponseEntity<Map<String,Object>> getUserLevel3(@RequestBody User user, @PathVariable Long idUserInitial){
        Map<String, Object> map = new LinkedHashMap<>();
        List<User> users = redService.getUsersByPreferencesOfTwoUsers(user.getId(), idUserInitial);
        if(users.size() > 0){
            map.put("users", users);
            map.put("itemsUser2", redService.getItemsToUser(user.getId(),idUserInitial));
            return new ResponseEntity<>(map, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/propuesta/paso4/{idUserInitial}/{idUser2}")
    public ResponseEntity<Map<String,Object>> getItemsUser3(@RequestBody User user,
                                                            @PathVariable Long idUserInitial, @PathVariable Long idUser2){
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("itemsUser1", redService.getItemsToUser(idUserInitial,user.getId()));
        map.put("itemsUser3", redService.getItemsToUser(user.getId(),idUser2));
        return new ResponseEntity<>(map, HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.POST, value = "/enviarPropuesta/{user1}/{user2}/{user3}")
    public ResponseEntity registerTrueque(@RequestBody Map<Integer,List<Item>> participants,
                                          @PathVariable Long user1,@PathVariable Long user2,@PathVariable Long user3){
        truequeService.saveTrueque(participants,user1,user2,user3);
        return new ResponseEntity(HttpStatus.OK);
    }



}
