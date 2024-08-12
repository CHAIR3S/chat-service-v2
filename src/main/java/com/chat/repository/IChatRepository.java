package com.chat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.chat.entity.chatviaje;
import com.chat.entity.usuario;

@Repository
public interface IChatRepository  extends JpaRepository<chatviaje, Integer>{
	

    List<chatviaje> findByViaje_ConductorOrViaje_Cliente(usuario conductor, usuario cliente);

}
