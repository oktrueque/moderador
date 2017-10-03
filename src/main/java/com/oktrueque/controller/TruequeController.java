package com.oktrueque.controller;

import com.oktrueque.service.TruequeService;
import com.oktrueque.service.TruequeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Envy on 6/7/2017.
 */
@Controller
public class TruequeController {

    @Autowired
    private TruequeService truequeService;

    @RequestMapping(method = RequestMethod.GET, value = "/trueques")
    public String getTrueques(Model model){
        model.addAttribute("trueques", truequeService.getAllTrueques());
        return "trueques";
    }
}
