package com.example.service;

import java.util.List;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.entity.Message;
import com.example.repository.MessageRepository;

@Service // this is the proper annotation for this component
@Transactional // because i wnat eerithing to be safe
public class MessageService {

    MessageRepository messgRep; // I will use the Rep for actually persisting data manipulated by the Service

    @Autowired // I need to inject Service in ????? (find out) so I auto wire them.
    public MessageService(MessageRepository messgRep) {
        this.messgRep = messgRep;
    }

    // ### Account
    // ```
    // account_id integer primary key auto_increment,
    // username varchar(255) not null unique,
    // password varchar(255)
    // ```

    // ### Message
    // ```
    // message_id integer primary key auto_increment,
    // posted_by integer,
    // message_text varchar(255),
    // time_posted_epoch long,
    // foreign key (posted_by) references Account(account_id)
    // ```

    // ## 3: Our API should be able to process the creation of new messages.
    public Message addMessage(Message message) {
        Message message2 = messgRep.save(message);
        return message2;
    }

    public List<Message> getAllMessages() {
        return messgRep.findAll();
    }

    public Message getById(Integer id) {
        return messgRep.findById(id).orElse(null);
    }

    public void deleteMessageById(Integer message_id) {
        messgRep.deleteById(message_id);
    }

    public void updateMessageById(Integer message_id, Message message) {

        message.setMessage_id(message_id);
        message.setPosted_by(messgRep.getById(message_id).getPosted_by());
        messgRep.save(message);
    }

    public List<Message> getAllMessagesByPosted_by(int posted_by) {
        return messgRep.findAll();
    
    }

    // ## 4: Our API should be able to retrieve all messages.
    // public List<Message> getAllMesagges() {
    // return messgRep.findAll();
    // }

    // ## 5: Our API should be able to retrieve a message by its ID.

    // ## 6: Our API should be able to delete a message identified by a message ID.

    // ## 7: Our API should be able to update a message text identified by a message
    // ID.

    // ## 8: Our API should be able to retrieve all messages written by a particular
    // user.

}
