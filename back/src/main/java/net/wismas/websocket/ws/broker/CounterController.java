package net.wismas.websocket.ws.broker;

import net.wismas.websocket.Model;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

/**
 * Created by conta on 04/05/2017.
 */

@Controller
public class CounterController {

    @MessageMapping("/ws/hello")
    @SendTo("/topic/greetings")
    public Model greeting() throws Exception {
        System.out.println("hello received");
        Thread.sleep(1000); // simulated delay
        System.out.println("sending broker 1");
        return new Model("hello " + 1);
    }

}
