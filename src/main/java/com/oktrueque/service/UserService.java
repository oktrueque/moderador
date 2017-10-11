package com.oktrueque.service;

import com.oktrueque.model.Complaint;
import com.oktrueque.model.Email;
import com.oktrueque.model.User;
import com.oktrueque.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {

    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private StorageService storageService;
    private EmailService emailService;
    private ItemServiceImpl itemService;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, StorageService storageService, EmailService emailService, ItemServiceImpl itemService){
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.storageService = storageService;
        this.emailService = emailService;
        this.itemService = itemService;
    }

    public List<User> getUsers() {
        return (List<User>) userRepository.findAll();
    }

    public User addUser(User user, MultipartFile image){
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        String imageUrl = "";
        try {
            imageUrl = storageService.store(image, user);
        } catch (Exception e) {
            //model.addAttribute("message", "FAIL to upload " + image.getOriginalimagename() + "!");
        }
        user.setPhoto1(imageUrl);
        return userRepository.save(user);
    }

    public void updateUser(User user){
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Transactional
    public void deleteUser(Long id){
        itemService.deleteItemsByUserId(id);
        userRepository.delete(id);
    }

    public User getUserByUsername(String username){
        return userRepository.findByUsername(username);
    }

    public List<User> findUsersByStatus(Integer status) {
        return userRepository.findByStatus(status);
    }

    public List<User> findUsersByIds(List<Complaint> complaints){
        List<Long> usersIds = new ArrayList<Long>();
        for (Complaint queja : complaints) {
            usersIds.add(queja.getUser_origin().getId());
        }
        return userRepository.findAllByIdIn(usersIds);
    }

    public Boolean checkIfUserExists(String email, String username){
        return userRepository.checkIfUserExists(email, username)>0;
    }

    public void sendEmailToUser(User userTarget, String subject, String text){
        Email email = new Email();
        email.setMailTo(userTarget.getEmail());
        email.setMailSubject(subject);

        Map< String, Object > model = new LinkedHashMap<>();

        model.put("text", text);


        // model.put("uri_confirm","http://localhost:8080/trueque/"+trueque.getId()+"/confirm");
        email.setModel(model);
        emailService.sendMail(email,"mailAdminToUser.ftl");

    }
}
