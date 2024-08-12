package com.chat.dto;

import com.chat.entity.usuario;
import com.chat.entity.viaje;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatDTO {
	private Integer idChat;

	private viaje viaje;

	private Integer idUsuario;

}
