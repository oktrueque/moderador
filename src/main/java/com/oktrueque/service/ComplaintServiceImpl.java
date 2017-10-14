package com.oktrueque.service;

import com.oktrueque.model.Complaint;
import com.oktrueque.repository.ComplaintRepository;

import java.util.ArrayList;
import java.util.List;


public class ComplaintServiceImpl implements ComplaintService{

    private final ComplaintRepository complaintRepository;


    public ComplaintServiceImpl(ComplaintRepository complaintRepository) {
        this.complaintRepository = complaintRepository;

    }

    @Override
    public List<Complaint> getAllComplaints() {
        List<Complaint> complaints = new ArrayList<>();
        complaintRepository.findAll().forEach(complaints::add);
        return complaints;
    }

    @Override
    public List<Complaint> getComplaintsByUserTarget(Long Id){
        return complaintRepository.findAllByUserTargetId(Id);
    }

    @Override
    public Complaint getComplaintById(Long id) {
        return complaintRepository.findOne(id);
    }

    @Override
    public void deleteComplaint(Long id) {
        complaintRepository.delete(id);
    }
}
