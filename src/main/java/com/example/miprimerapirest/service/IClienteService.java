package com.example.miprimerapirest.service;

import com.example.miprimerapirest.model.dto.ClienteDto;
import com.example.miprimerapirest.model.entity.Cliente;

public interface IClienteService {

    Cliente save(ClienteDto cliente);

    Cliente findById(Integer id);


    void delete(Cliente cliente);

    boolean existsById(Integer id);

}
