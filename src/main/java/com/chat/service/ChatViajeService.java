package com.chat.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chat.dto.ChatDTO;
import com.chat.entity.chatviaje;
import com.chat.entity.usuario;
import com.chat.repository.IChatRepository;
import com.chat.repository.IUsuarioRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ChatViajeService implements IChatViajeService{

	@Autowired 
	IChatRepository chatRepository;
	
	@Autowired 
	IUsuarioRepository usuarioRepository;
	
	@Override
	public List<ChatDTO> obtenerChatsUsuario(Integer idUsuario) {
		List<ChatDTO> chatDtoList = new ArrayList<>();
		
		Optional<usuario> usuarioOptional = usuarioRepository.findById(idUsuario);
		
		if(usuarioOptional.isEmpty()) {
			return new ArrayList<ChatDTO>();
		}
		
		List<chatviaje> chatViajeList = chatRepository.findByViaje_ConductorOrViaje_Cliente(usuarioOptional.get(), usuarioOptional.get());
		
		chatViajeList.forEach(chat -> {
			ChatDTO chatDto = new ChatDTO();
			
			chatDto.setIdChat(chat.getIdChat());
			chatDto.setIdUsuario(idUsuario);
			chatDto.setViaje(chat.getViaje());
			
			chatDtoList.add(chatDto);
		});
		
		return chatDtoList;
	}

}
