package com.oktrueque.service;

import com.oktrueque.model.*;
import com.oktrueque.repository.ItemTruequeRepository;
import com.oktrueque.repository.TruequeRepository;
import com.oktrueque.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import com.oktrueque.repository.UserTruequeRepository;

import javax.transaction.Transactional;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Envy on 8/7/2017.
 */
public class TruequeServiceImpl implements TruequeService {

    @Value("${api.url}")
    private String urlServer;

    private final TruequeRepository truequeRepository;
    private final ItemTruequeRepository itemTruequeRepository;
    private final UserTruequeRepository userTruequeRepository;
    private final UserRepository userRepository;
    private final EmailService emailService;

    public TruequeServiceImpl(TruequeRepository truequeRepository, ItemTruequeRepository itemTruequeRepository, UserTruequeRepository userTruequeRepository, UserRepository userRepository, EmailService emailService){
        this.truequeRepository = truequeRepository;
        this.itemTruequeRepository = itemTruequeRepository;
        this.userTruequeRepository = userTruequeRepository;
        this.userRepository = userRepository;
        this.emailService = emailService;
    }

    @Override
    public List<Trueque> getAllTrueques(){
        return (List<Trueque>) this.truequeRepository.findAll();
    }

    @Override
    public List<UserTrueque> getUserTruequeById_UserId(long id){
        return userTruequeRepository.getUserTruequeById_UserId(id);
    }

    @Override
    public Trueque getTruequeById (long id){
        return truequeRepository.findTruequeById(id);
    }

    @Override
    @Transactional
    public void saveTrueque(Map<Integer, List<Item>> participants, Long idUser1, Long idUser2, Long idUser3) {
        Trueque truequeToSave = new Trueque(0);
        truequeToSave.setPeopleCount(participants.size());
        Trueque truequeSaved = truequeRepository.save(truequeToSave);
        setUsers(participants,idUser1,1);
        setUsers(participants,idUser2,2);
        setUsers(participants,idUser3,3);
        saveItemsAndUsers(participants, truequeSaved);
        User user1 = userRepository.findOne(idUser1);
        User user2 = userRepository.findOne(idUser2);
        User user3 = userRepository.findOne(idUser3);
        sendMailTo(user1,user2,user3,participants.get(2),participants.get(1),truequeSaved);
        sendMailTo(user2,user3,user1,participants.get(3),participants.get(2),truequeSaved);
        sendMailTo(user3,user1,user2,participants.get(1),participants.get(3),truequeSaved);
    }

    private void setUsers(Map<Integer, List<Item>> participants, Long user1, Integer key) {
        participants.get(key).stream().forEach(item->{
            User user = new User();
            user.setId(user1);
            item.setUser(user);
        });
    }

    private void saveItemsAndUsers(Map<Integer, List<Item>> participants, Trueque truequeSaved) {
        participants.entrySet().forEach(entry -> {
            userTruequeRepository.save(
                    createUserTrueque(truequeSaved, entry.getValue().get(0).getUser(), entry.getKey()));
            entry.getValue().forEach(item ->
                    itemTruequeRepository.save(createItemTrueque(truequeSaved, item)));
        });
    }

    private UserTrueque createUserTrueque(Trueque truequeSaved, User user, Integer orden) {
        UserTrueque userTrueque = new UserTrueque(new UserTruequeId(truequeSaved.getId(), user.getId()), orden, false);
        return userTrueque;
    }

    private ItemTrueque createItemTrueque(Trueque truequeSaved, Item item) {
        return new ItemTrueque(new ItemTruequeId(truequeSaved.getId(), item.getId()));
    }

    private void sendMailTo(User userDestinoMail,User userOfertante,  User userDestinoItems,
                            List<Item> itemsPropuestos, List<Item> itemsDemandados, Trueque trueque){
        Email email = new Email();
        email.setMailTo(userDestinoMail.getEmail());
        email.setMailSubject("Nueva propuesta de Trueque");
        Map< String, Object > model = new LinkedHashMap<>();
        model.put("nombreDestino", userDestinoMail.getName());
        model.put("apellidoDestino", userDestinoMail.getLastName());
        model.put("userNombreDestino", userDestinoItems.getName());
        model.put("userApellidoDestino", userDestinoItems.getLastName());
        model.put("userNombreOfertante", userOfertante.getName());
        model.put("userApellidoOfertante", userOfertante.getLastName());
        model.put("itemsPropuestos", itemsPropuestos);
        model.put("itemsDemandados", itemsDemandados);
        model.put("uri_confirm",urlServer + "trueques/"+trueque.getId()+"/confirm");
        email.setModel(model);
        emailService.sendMail(email,"truequeRequest.ftl");

    }


}
