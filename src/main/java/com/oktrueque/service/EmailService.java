package com.oktrueque.service;

import com.oktrueque.model.Email;
import com.oktrueque.model.User;

/**
 * Created by Facundo on 12/07/2017.
 */
public interface EmailService {

    void sendMail(Email email, String template);

}
