package com.chat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chat.entity.chatviaje;
import com.chat.entity.mensajeschat;

@Repository
public interface IMensajesRepository extends JpaRepository<mensajeschat, Integer>{
	
	public List<mensajeschat> findByChat(chatviaje chat);

}
