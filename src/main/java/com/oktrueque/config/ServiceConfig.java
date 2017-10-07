package com.oktrueque.config;

import com.oktrueque.repository.*;
import com.oktrueque.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


/**
 * Created by Facundo on 12/07/2017.
 */
@Configuration
@EntityScan(basePackageClasses = {Jsr310JpaConverters.class}, basePackages = "com.oktrueque.model")
public class ServiceConfig {

    @Autowired
    private ComplaintTypeRepository complaintTypeRepository;
    @Autowired
    private ComplaintRepository complaintRepository;
    @Autowired
    private UserTagRepository userTagRepository;
    @Autowired
    private TagRepository tagRepository;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private ItemTagRepository itemTagRepository;
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private StorageService storageService;
    @Autowired
    private TruequeRepository truequeRepository;
    @Autowired
    private UserTruequeRepository userTruequeRepository;
    @Autowired
    private ItemTruequeRepository itemTruequeRepository;
//    @Autowired
//    private CategoryRepository categoryRepository;


    @Bean
    public ComplaintTypeService complaintTypeService(){return new ComplaintTypeServiceImpl(complaintTypeRepository);}

    @Bean
    public ComplaintService complaintService(){
        return new ComplaintServiceImpl(complaintRepository);
    }

    @Bean
    public UserTagService userTagService() {
        return new UserTagServiceImpl(userTagRepository,this.TagService());
    }

    @Bean
    public TagService TagService() {
        return new TagServiceImpl(tagRepository);
    }

    @Bean
    public ItemTagService ItemTagService() {
        return new ItemTagServiceImpl(itemTagRepository);
    }

    @Bean
    public RedService redService(){
        return new RedServiceImpl(userRepository,tagRepository,userTagRepository,itemRepository, itemTagRepository);
    }

    @Bean
    public EmailService emailService(){
        return new EmailServiceImpl(javaMailSender);
    }


    @Bean
    public UserService userService() { return  new UserService(userRepository, bCryptPasswordEncoder, storageService, this.emailService());}

    @Bean
    public TruequeService truequeService(){
        return new TruequeServiceImpl(truequeRepository, itemTruequeRepository, userTruequeRepository, userRepository, this.emailService());
    }

    @Bean
    public ReportService reportService(){
        return new ReportServiceImpl(itemRepository, userRepository, truequeRepository);
    }
}
