package net.wismas.websocket.ws.broker;

import net.wismas.websocket.model.HelloMessage;
import net.wismas.websocket.model.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

/**
 * Created by conta on 04/05/2017.
 */

@Controller
public class CounterController {

    @MessageMapping("/ws/hello")
    @SendTo("/topic/greetings")
    public Model greeting(HelloMessage message) throws Exception {
        System.out.println("hello received " + message.getName());
        Thread.sleep(1000); // simulated delay
        System.out.println("sending broker 1");
        return new Model("hello " + message.getName());
    }


    @Autowired
    private ApplicationContext context;

    @MessageMapping("/ws/message/{userId}")
    public void messageToSpecificUser(@DestinationVariable String userId, HelloMessage message) throws InterruptedException {
        System.out.println("specific message received " + message.getName());
        message.setUserId(userId);

        MessageCreator messageObjectCreator = session -> session.createObjectMessage(message);
        JmsTemplate jmsTemplate = context.getBean(JmsTemplate.class);
        jmsTemplate.send("jms-message", messageObjectCreator);
    }

}
