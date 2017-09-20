package com.example.websocket.demo.controller;

import com.example.websocket.demo.service.WebSocketGreetingService;
import com.example.websocket.demo.transfer.ServerMessage;
import com.example.websocket.demo.transfer.UserMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class GreetingController {

    @Autowired
    private WebSocketGreetingService webSocketGreetingService;
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/bye")
    @SendTo("/topic/message")
    public ServerMessage sayBye(UserMessage userMessage) throws Exception {
        return new ServerMessage("Bye, " + userMessage.getName() + "!");
    }

    @MessageMapping("/hello")
    public void sayHi(UserMessage userMessage) throws Exception {
        webSocketGreetingService.sendGreetingTimes(5, userMessage.getName());
    }

    public void sendGreetingMessage(ServerMessage message){
        this.simpMessagingTemplate.convertAndSend("/topic/message", message);
    }

}