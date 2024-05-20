package com.example.miprimerapirest.controller;

import com.example.miprimerapirest.model.dto.ClienteDto;
import com.example.miprimerapirest.model.entity.Cliente;
import com.example.miprimerapirest.model.payload.MensajeResponse;
import com.example.miprimerapirest.service.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class ClienteController {

    @Autowired
    private IClienteService clienteService;

    @PostMapping("cliente")
    public ResponseEntity<?> create(@RequestBody ClienteDto clienteDto){
        Cliente clienteSave = null;
        try{
           clienteSave = clienteService.save(clienteDto);
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje("Guradado Correctamente")
                    .object(clienteDto.builder()
                                    .idCliente(clienteSave.getIdCliente())
                                    .nombre(clienteSave.getNombre())
                                    .apellido(clienteSave.getApellido())
                                    .correo(clienteSave.getCorreo())
                                    .fechaRegistro(clienteSave.getFechaRegistro())
                                    .build())
                    .build()
                    ,HttpStatus.CREATED);

        }catch (DataAccessException exDt){
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje(exDt.getMessage())
                            .object(null)
                            .build(), HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }


    @PutMapping("cliente/{id}")
    public ResponseEntity<?> update(@RequestBody ClienteDto clienteDto, @PathVariable Integer id){
      Cliente clienteUpdate = null;
        try{
            if(clienteService.existsById(id)){
                clienteDto.setIdCliente(id);
                clienteUpdate = clienteService.save(clienteDto);
                return new ResponseEntity<>(MensajeResponse.builder()
                        .mensaje("Editado Correctamente")
                        .object(ClienteDto.builder()
                                .idCliente(clienteUpdate.getIdCliente())
                                .nombre(clienteUpdate.getNombre())
                                .apellido(clienteUpdate.getApellido())
                                .correo(clienteUpdate.getCorreo())
                                .fechaRegistro(clienteUpdate.getFechaRegistro())
                                .build())
                        .build(), HttpStatus.CREATED);
            }
            else {
                return new ResponseEntity<>(
                        MensajeResponse.builder()
                                .mensaje("El registro que intenta actualizar no se encuentra en la base de datos")
                                .object(null)
                                .build(), HttpStatus.NOT_FOUND);
            }

        }catch (DataAccessException exDt){
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje(exDt.getMessage())
                            .object(null)
                            .build(), HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }


    //METODO BONITO CON EXCEPECIONES Y MENSAJES CUSTOMIZADOS
    @DeleteMapping("cliente/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){

        try{
            Cliente clienteDelete = clienteService.findById(id);
            clienteService.delete(clienteDelete);
            return new ResponseEntity<>(clienteDelete,HttpStatus.NO_CONTENT);

        }catch (DataAccessException exDt){
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje(exDt.getMessage())
                    .object(null)
                    .build(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("cliente/{id}")
    public ResponseEntity<?> showId (@PathVariable  Integer id){
        Cliente cliente = clienteService.findById(id);

        if(cliente == null){

            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje("EL registro que intenta buscar no existe")
                            .object(null)
                            .build(), HttpStatus.INTERNAL_SERVER_ERROR);

        }
        return new ResponseEntity<>(
                MensajeResponse.builder()
                        .mensaje("")
                        .object(ClienteDto.builder()
                                .idCliente(cliente.getIdCliente())
                                .nombre(cliente.getNombre())
                                .apellido(cliente.getApellido())
                                .correo(cliente.getCorreo())
                                .fechaRegistro(cliente.getFechaRegistro())
                                .build())
                        .build(), HttpStatus.OK);

    }


}
