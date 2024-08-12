package com.chat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chat.entity.usuario;

@Repository
public interface IUsuarioRepository extends JpaRepository<usuario, Integer> {

}
