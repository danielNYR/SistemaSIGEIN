package com.sigeiin.main.web.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "Inicio")
public class Index implements Serializable{
	
	private static final long SerialVersionUID = 1L;
    
    //Identificador de la tabla Aspirante
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idInicio")
    private Long idIndex;
    
    @Column(name ="adjuntoInicio")
    private String adjuntoIndex;
    
    @Column(name="tituloAdjunto")
    private String tituloAdjunto;
    
    @Column(name="subtituloAdjunto")
    private String subtituloAdjunto;
}
