package com.example.websocket.demo.service;

import com.example.websocket.demo.controller.GreetingController;
import com.example.websocket.demo.transfer.ServerMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WebSocketGreetingService {
    @Autowired
    GreetingController greetingController;

    public void sendGreetingTimes(int n, String name) throws Exception {
        for (int i = 0; i < n; i++) {
            this.greetingController.sendGreetingMessage(new ServerMessage("Hello, " + name + " " + (i + 1)));
            Thread.sleep(1000);
        }
    }
}
