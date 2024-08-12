package com.chat.entity;

import java.security.Timestamp;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "mensajeschat")
@Getter
@Setter
public class mensajeschat {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idMensaje")
	private Integer idMensaje;

	@ManyToOne
	@JoinColumn(name = "idChat", nullable = false)
	private chatviaje chat;

	@ManyToOne
	@JoinColumn(name = "idEmisor", nullable = false)
	private usuario emisor;

	@Column(name = "mensaje", nullable = false)
	private String mensaje;

	@Column(name = "fechaHora", nullable = false)
    private LocalDateTime fechaHora;
}
