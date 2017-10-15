package com.oktrueque.controller;

import com.oktrueque.service.TruequeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;
import java.util.Map;

@Controller
public class TruequeController {

    @Autowired
    private TruequeService truequeService;

    @RequestMapping(method = RequestMethod.GET, value = "/trueques")
    public String getTrueques(Model model){
        model.addAttribute("trueques", truequeService.getAllTrueques());
        return "trueques";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/trueques/{id}")
    public ResponseEntity<Map<String, Object>> getTrueque(@PathVariable Long id, Principal principal){
        Map map = truequeService.getTrueque(id);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
