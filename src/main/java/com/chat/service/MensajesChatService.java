package com.chat.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chat.dto.MensajeDTO;
import com.chat.entity.chatviaje;
import com.chat.entity.mensajeschat;
import com.chat.entity.usuario;
import com.chat.repository.IChatRepository;
import com.chat.repository.IMensajesRepository;
import com.chat.repository.IUsuarioRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MensajesChatService implements IMensajesChatService {

	@Autowired
	IMensajesRepository mensajeRepository;
	
	@Autowired
	IChatRepository chatRepository;
	
	@Autowired
	IUsuarioRepository usuarioRepository;

	@Override
	public void guardarMensaje(String mensaje) {
		log.info("GUARDANDO MENSAJE");

		ObjectMapper objectMapper = new ObjectMapper();

		try {
			 // Deserializa el JSON a un DTO en lugar de a la entidad
	        MensajeDTO mensajeDTO = objectMapper.readValue(mensaje, MensajeDTO.class);
	        chatviaje chat = chatRepository.findById(mensajeDTO.getChatId()).orElseThrow();
	        usuario emisor = usuarioRepository.findById(mensajeDTO.getEmisorId()).orElseThrow();

	        mensajeschat nuevoMensaje = new mensajeschat();
	        nuevoMensaje.setIdMensaje(mensajeDTO.getIdMensaje());
	        nuevoMensaje.setChat(chat);
	        nuevoMensaje.setEmisor(emisor);
	        nuevoMensaje.setMensaje(mensajeDTO.getMensaje());
	        nuevoMensaje.setFechaHora(LocalDateTime.parse(mensajeDTO.getFechaHora().substring(0, mensajeDTO.getFechaHora().length() - 1)));
	        
	        
			mensajeRepository.save(nuevoMensaje);

			log.info("Mensaje Guardado Correctamente");
		} catch (Exception e) {
			log.info("Error al crear la mensaje: " + e.getMessage());
			return;
		}

	}

	@Override
	public List<MensajeDTO> getMessagesByChatId(Integer idChat) {
		
		Optional<chatviaje> chatOptional = chatRepository.findById(idChat);
		
		if(chatOptional.isEmpty()) {
			log.error("Error: Chat no encontrado");
			return new ArrayList<>();
		}
		
		List<mensajeschat> mensajesList = mensajeRepository.findByChat(chatOptional.get());
		
		List<MensajeDTO> mensajeDTOList = new ArrayList<>();
		
		mensajesList.forEach(mensaje -> {
			
	        MensajeDTO nuevoMensaje = new MensajeDTO();
	        nuevoMensaje.setIdMensaje(mensaje.getIdMensaje());
	        nuevoMensaje.setChatId(mensaje.getChat().getIdChat());
	        nuevoMensaje.setEmisorId(mensaje.getEmisor().getIdUsuario());
	        nuevoMensaje.setMensaje(mensaje.getMensaje());
	        nuevoMensaje.setFechaHora(mensaje.getFechaHora().toString());
			
			mensajeDTOList.add(nuevoMensaje);
		});
		
		return mensajeDTOList;
	}

}
