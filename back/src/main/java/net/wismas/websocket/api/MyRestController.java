package net.wismas.websocket.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by alexis.delaporte on 28/04/2017.
 */
@RestController
public class MyRestController {
    @RequestMapping("/api/ping")
    @ResponseBody
    String home() {
        return "Hello World!";
    }
}
