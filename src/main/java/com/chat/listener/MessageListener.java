package com.chat.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.chat.service.IMensajesChatService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class MessageListener {

    
    @Value("${rabbitmq.queue}")
    String  queueName;

    @Value("${rabbitmq.exchange}")
    String exchange;
    
	@Autowired
	private IMensajesChatService mensajesService;

    
    @RabbitListener(queues = "${rabbitmq.queue}")
    public void handleMessage(String message) {
    	
    	log.info("-----Mensaje recibido en la queue " + message);
        
        try {
        	mensajesService.guardarMensaje(message);
		} catch (Exception e) {
			log.info("-----" + e.getMessage());
		}
        
    }
     
}
