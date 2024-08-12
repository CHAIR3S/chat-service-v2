package com.chat.service;

import java.util.List;

import com.chat.dto.MensajeDTO;
import com.chat.entity.mensajeschat;

public interface IMensajesChatService {
	
	/**
	 * 
	 * @param Mensaje
	 * @throws ServiceException
	 */
	void guardarMensaje(String mensaje);
	
	
	public List<MensajeDTO> getMessagesByChatId(Integer idChat);
}
