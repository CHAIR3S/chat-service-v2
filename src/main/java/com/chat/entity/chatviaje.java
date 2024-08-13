package com.chat.entity;

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
@Table(name = "chatviaje")
@Getter
@Setter
public class chatviaje {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idChat")
	private Integer idChat;
	
	@Column(name= "status")
	private Integer status;

	@ManyToOne
	@JoinColumn(name = "idViaje", nullable = false)
	private viaje viaje;

	@ManyToOne
	@JoinColumn(name = "idConductor")
	private usuario conductor;

//	@ManyToOne
//	@JoinColumn(name = "idCliente")
//	private usuario cliente;
}
