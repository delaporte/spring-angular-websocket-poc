package net.wismas.websocket.jms;

import net.wismas.websocket.model.HelloMessage;
import net.wismas.websocket.model.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

/**
 * Created by alexis.delaporte on 05/05/2017.
 */
@Component
public class MessageListener {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @JmsListener(destination = "jms-message")
    public void receiveMailModel(HelloMessage message) throws InterruptedException {
        System.out.println("jms-message <" + message + ">");

        Thread.sleep(3000);
        simpMessagingTemplate.convertAndSend("/topic/message/" + message.getUserId(), new Model("coucou " + message.getUserId() + message.getName()));
    }
}
