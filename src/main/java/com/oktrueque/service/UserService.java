package com.oktrueque.service;

import com.oktrueque.model.Complaint;
import com.oktrueque.model.User;
import com.oktrueque.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Felipe on 7/5/2017.
 */
@Service
public class UserService {

    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private StorageService storageService;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, StorageService storageService){
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.storageService = storageService;
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

    public void deleteUser(Long id){
        User user = userRepository.findOne(id);
        user.setStatus(3);
        userRepository.save(user);
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
}
