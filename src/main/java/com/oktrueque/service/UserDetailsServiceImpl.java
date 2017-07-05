package com.oktrueque.service;

import com.oktrueque.model.Admin;
import com.oktrueque.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by Envy on 3/7/2017.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private AdminRepository adminRepository;

    public UserDetailsServiceImpl(AdminRepository adminRepository){
        this.adminRepository=adminRepository;
    }

    @Autowired
    public void setAdminRepository(AdminRepository adminRepository){
        this.adminRepository = adminRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Admin admin = adminRepository.findByUsername(username);
        if (admin == null) {
            throw new UsernameNotFoundException(username);
        }
        return admin;
    }
}