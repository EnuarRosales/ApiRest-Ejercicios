package com.example.miprimerapirest.model.dao;

import com.example.miprimerapirest.model.entity.Cliente;
import jakarta.persistence.Index;
import org.springframework.data.repository.CrudRepository;


//CREACION DEL REPOSITORIO
public interface ClienteDao extends CrudRepository<Cliente, Integer> {



}
