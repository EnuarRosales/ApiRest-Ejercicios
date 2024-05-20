package com.example.miprimerapirest.model.entity;


import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Data //GETTERS AND SETTERS
@AllArgsConstructor //CONSTRUCTOR
@NoArgsConstructor //CONSTRUCTOR SIN ARGUMENTOS
@ToString  //TOOSTRING
@Builder

@Entity //MAPEO
@Table(name = "clientes") //MAPEO
public class Cliente implements Serializable {

    @Id
    @Column(name = "id_cliente")
    @GeneratedValue(strategy = GenerationType.IDENTITY) //QUE GENERE AUTOINCREMENTABLE
    private  Integer idCliente;

    @Column(name = "nombre") //MAPEO CON EL NOMBRE DE COLUMAN DE LA BASE DE DATOS
    private  String nombre;

    @Column(name = "apellido")
    private  String apellido;

    @Column(name = "correo")
    private  String correo;

    @Column(name = "fecha_registro")
    private Date  fechaRegistro;


}
