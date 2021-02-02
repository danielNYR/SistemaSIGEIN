/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigeiin.main.web.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

/**
 *
 * @author Danie
 */
@Data
@Entity
@Table(name = "inicio")
public class Inicio implements Serializable{
    
    private static final long SerialVersionUID = 1L;
    
    //Identificador de la tabla Aspirante
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
