package com.example.miprimerapirest.model.payload;


import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import javax.sql.rowset.serial.SerialStruct;
import java.io.Serializable;

@Data //GETTERS AND SETTERS
@ToString  //TOOSTRING
@Builder

public class MensajeResponse implements Serializable {

    private String mensaje;
    private  Object object;

}
