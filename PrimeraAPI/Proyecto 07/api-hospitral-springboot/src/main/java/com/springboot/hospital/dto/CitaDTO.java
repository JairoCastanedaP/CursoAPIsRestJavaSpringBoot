package com.springboot.hospital.dto;

import lombok.Data;

@Data
public class CitaDTO {

    private Long id;
    private String fecha;
    private String statusCita;
    private boolean cancelado;
    private Long pacienteId;
    private Long medicoId;

}
