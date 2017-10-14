package com.oktrueque.controller;

import com.oktrueque.model.Complaint;
import com.oktrueque.service.ComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

    @RequestMapping(method = RequestMethod.GET, value = "/complaints/{id}")
    public ResponseEntity<Complaint> getComplaint(@PathVariable Long id){
        Complaint complaint = complaintService.getComplaintById(id);
        return new ResponseEntity<>(complaint, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/complaints/{id}")
    public ResponseEntity<Void> deleteComplaint(@PathVariable Long id){
        complaintService.deleteComplaint(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
