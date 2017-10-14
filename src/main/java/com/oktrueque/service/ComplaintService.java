package com.oktrueque.service;

import com.oktrueque.model.Complaint;

import java.util.List;

public interface ComplaintService {

    List<Complaint> getAllComplaints();

    List<Complaint> getComplaintsByUserTarget(Long Id);

    Complaint getComplaintById(Long id);

    void deleteComplaint(Long id);
}
