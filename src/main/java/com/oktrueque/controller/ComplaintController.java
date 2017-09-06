package com.oktrueque.controller;

import com.oktrueque.service.ComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Fabrizio SPOSETTI on 03/09/2017.
 */
@Controller
public class ComplaintController {

    private ComplaintService complaintService;

    @Autowired
    public ComplaintController(ComplaintService complaintService) {
        this.complaintService = complaintService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/complaints")
    public String getAllComplaints(Model model){

        model.addAttribute("complaints" , complaintService.getAllComplaints());
        return "complaints";
    }

}
