package net.wismas.websocket.ws.broker;

import net.wismas.websocket.model.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by conta on 04/05/2017.
 */
@Component
public class ScheduledBroker {
    @Autowired
    private SimpMessagingTemplate template;


    @Scheduled(fixedDelay=5000)
    public void publishUpdates(){
        template.convertAndSend("/topic/greetings", new Model("coucou"));
    }
}
