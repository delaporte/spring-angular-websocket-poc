package net.wismas.websocket.domain;

import net.wismas.websocket.ws.handler.CounterHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by alexis.delaporte on 28/04/2017.
 */
@Component
public class CounterService {
    private AtomicInteger counter = new AtomicInteger(0);

    @Autowired
    CounterHandler counterHandler;

    @Scheduled(fixedDelay = 10000)
    public void sendCounterUpdate() {
        counterHandler.counterIncrementedCallback(counter.incrementAndGet());
    }
}
