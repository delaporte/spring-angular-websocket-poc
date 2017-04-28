package net.wismas.websocket.ws;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexis.delaporte on 28/04/2017.
 */
@Component
public class CounterHandler extends TextWebSocketHandler {

    List<WebSocketSession> session = new ArrayList<>();

    // This will send only to one client(most recently connected)
    public void counterIncrementedCallback(int counter) {
        System.out.println("Trying to send:" + counter);
        for (WebSocketSession webSocketSession : this.session) {
            if (webSocketSession != null && webSocketSession.isOpen()) {
                try {
                    System.out.println("Now sending:" + counter);
                    webSocketSession.sendMessage(new TextMessage("{\"value\": \"" + counter + "\"}"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("Don't have open session to send:" + counter);
            }
        }
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        System.out.println("Connection established " + session.getId());
        this.session.add(session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        System.out.println("close : " + session.getId());
        this.session.remove(session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message)
            throws Exception {
        if ("CLOSE".equalsIgnoreCase(message.getPayload())) {
            session.close();
        } else {
            System.out.println("Received:" + message.getPayload());
        }
    }
}
