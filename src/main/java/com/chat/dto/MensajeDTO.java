package com.chat.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MensajeDTO {
    private Integer idMensaje;
    private Integer chatId;
    private Integer emisorId;
    private String mensaje;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String fechaHora;
}
