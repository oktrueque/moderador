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
    private TruequeRepository truequeRepository;
    @Autowired
    private UserTruequeRepository userTruequeRepository;
    @Autowired
    private ItemTruequeRepository itemTruequeRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CommentRepository commentRepository;


    @Bean
    public CommentService commentService(){
        return new CommentServiceImpl(commentRepository);
    }

    @Bean
    public ComplaintTypeService complaintTypeService(){return new ComplaintTypeServiceImpl(complaintTypeRepository);}

    @Bean
    public ComplaintService complaintService(){
        return new ComplaintServiceImpl(complaintRepository);
    }

    @Bean
    public ItemServiceImpl itemService(){
        return new ItemServiceImpl(itemRepository);
    }

    @Bean
    public AwsS3Service awsS3Service(){
        return new AwsS3Service();
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
    public TruequeService truequeService(){
        return new TruequeServiceImpl(truequeRepository, itemTruequeRepository, userTruequeRepository, userRepository, this.emailService());
    }

    @Bean
    public ReportService reportService(){
        return new ReportServiceImpl(itemRepository, userRepository,truequeRepository,categoryRepository,complaintRepository);
    }
}
