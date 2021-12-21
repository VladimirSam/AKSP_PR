package com.java.example;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class SendingController {

    @MessageMapping("/web")
    @SendTo("/topic/sending")
    public Sending sending(Message message) throws Exception {
        Thread.sleep(1000);
        return new Sending("Имя: " + message.getUsername() + "\n" + "Сообщение: " + message.getMess());
    }
}
