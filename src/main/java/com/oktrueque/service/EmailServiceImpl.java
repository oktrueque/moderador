package com.oktrueque.service;

import com.oktrueque.model.Email;
import com.oktrueque.model.Trueque;
import com.oktrueque.model.User;
import com.oktrueque.model.UserTrueque;
import com.oktrueque.repository.TruequeRepository;
import com.oktrueque.repository.UserRepository;
import com.oktrueque.repository.UserTruequeRepository;
import freemarker.template.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;


import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class EmailServiceImpl implements EmailService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmailServiceImpl.class);
    private static final String MESSAGE= "Error sending email";
    private static final String SUCCESS= "Email send";

    private final JavaMailSender javaMailSender;
    private final UserRepository userRepository;
    private final TruequeRepository truequeRepository;
    private final UserTruequeRepository userTruequeRepository;

    @Autowired
    private Configuration fmConfiguration;

    public EmailServiceImpl(JavaMailSender javaMailSender, UserRepository userRepository, TruequeRepository truequeRepository, UserTruequeRepository userTruequeRepository) {
        this.javaMailSender = javaMailSender;
        this.userRepository = userRepository;
        this.truequeRepository = truequeRepository;
        this.userTruequeRepository = userTruequeRepository;
    }

    @Async
    @Override
    public void sendMail(Email mail, String template) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setSubject(mail.getMailSubject());
            mimeMessageHelper.setTo(mail.getMailTo());
            mail.setMailContent(geContentFromTemplate(mail.getModel(), template));
            mimeMessageHelper.setText(mail.getMailContent(), true);
            javaMailSender.send(mimeMessageHelper.getMimeMessage());
        } catch (MessagingException e) {
            LOGGER.warn(MESSAGE, e);
        }
        LOGGER.info(SUCCESS);
    }

    private String geContentFromTemplate(Map < String, Object > model, String template) {
        StringBuffer content = new StringBuffer();
        try {
            content.append(FreeMarkerTemplateUtils
                    .processTemplateIntoString(fmConfiguration.getTemplate(template), model));
        } catch (Exception e) {
            LOGGER.warn(MESSAGE, e);
        }
        return content.toString();
    }


    public ResponseEntity contact(Email email){
        Map<String,Object> model = new LinkedHashMap<>();
        model.put("emailContent",email.getMailContent());
        email.setModel(model);
        sendMail(email,"contactUs.ftl");
        return new ResponseEntity(HttpStatus.OK);
    }

    @Scheduled(cron = "0 0 21 * * mon,wed,fri,sat") //Todos los lun, mie, vie, dom a las 21hs.
    @Override
    public void notifyTrueques() {
        List<User> usuarios = userRepository.usersWithPendingTrueques(4,0,3);
        Email email = new Email();
        Map<String,Object> model = new LinkedHashMap<>();
        for(User user:usuarios){
            email.setMailTo(user.getEmail());
            email.setMailSubject("Estado de Trueques - OkTrueques");
            model.put("emailContent",email.getMailContent());
            model.put("userName",user.getName());
            email.setModel(model);
            sendMail(email,"activeTruequeNotification.ftl");
        }
    }
}