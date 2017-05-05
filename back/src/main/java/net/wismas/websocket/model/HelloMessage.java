package net.wismas.websocket.model;

import java.io.Serializable;

/**
 * Created by alexis.delaporte on 05/05/2017.
 */
public class HelloMessage implements Serializable {
    private String userId;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
