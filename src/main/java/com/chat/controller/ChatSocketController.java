package com.chat.controller;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.chat.dto.MensajeDTO;
import com.chat.service.MensajesChatService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class ChatSocketController {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    
    @Autowired
    private MensajesChatService mensajeService;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    @MessageMapping("/chat/{idChat}")
    public void processChatRequest(Integer idChat) {
    	log.info("obtener mensajes por id chat: {} " + idChat);

        scheduler.scheduleAtFixedRate(() -> {
        	log.info("actualizando chat: " + idChat);
            List<MensajeDTO> messages = mensajeService.getMessagesByChatId(idChat);
            messagingTemplate.convertAndSend("/topic/messages/" + idChat, messages);	
        }, 0, 1, TimeUnit.SECONDS); // Ejecuta cada segundo
    }

    @MessageMapping("/sendMessage")
    @SendTo("/topic/messages")
    public String sendMessage(String message) {
        rabbitTemplate.convertAndSend("CHAT_EXCHANGE", "CHAT_QUEUE", message);
        return message;
    }
    
    
    
}
