package com.chat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chat.dto.ChatDTO;
import com.chat.service.IChatViajeService;

@RestController
@CrossOrigin("*")
public class ChatController {
	
	@Autowired 
	IChatViajeService service;
	
	@GetMapping("/conversaciones/{idUsuario}")
	public ResponseEntity<List<ChatDTO>> obtenerConversaciones(@PathVariable Integer idUsuario) {
	    List<ChatDTO> conversaciones = service.obtenerChatsUsuario(idUsuario);
	    return ResponseEntity.ok(conversaciones);
	}
	

}
