package com.oktrueque.model;

import com.oktrueque.utils.Constants;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "complaint")
public class Complaint {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String description;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
    @ManyToOne
    @JoinColumn(name = "id_complaint_type")
    private ComplaintType complaintType;
    @ManyToOne
    @JoinColumn(name = "id_user_target")
    private User userTarget;
    @ManyToOne
    @JoinColumn(name = "id_user_origin")
    private User userOrigin;
    @Column(name = "status")
    private Integer status;

    public ComplaintType getComplaintType() {
        return complaintType;
    }

    public void setComplaintType(ComplaintType complaintType) {
        this.complaintType = complaintType;
    }


    public Complaint(){};


    public User getUserOrigin() {
        return userOrigin;
    }

    public void setUserOrigin(User userOrigin) {
        this.userOrigin = userOrigin;
    }

    public Complaint(String description, Date date, ComplaintType complaintType, User user_target, User user_origin) {
        this.description = description;
        this.date = date;
        this.complaintType = complaintType;
        this.userTarget = user_target;
        this.userOrigin = user_origin;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getUserTarget() {
        return userTarget;
    }

    public void setUserTarget(User userTarget) {
        this.userTarget = userTarget;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getStatusValue(){
        return Constants.getComplaintStatusName(this.status);
    }
}
