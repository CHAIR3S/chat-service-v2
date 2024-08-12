package com.chat.service;

import java.util.List;

import com.chat.dto.ChatDTO;

public interface IChatViajeService {
	
	public List<ChatDTO> obtenerChatsUsuario(Integer idUsuario);

}
