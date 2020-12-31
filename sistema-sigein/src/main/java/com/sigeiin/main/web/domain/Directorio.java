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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;

/**
 *
 * @author Danie
 */
@Data
@Entity
@Table(name="directorio")
public class Directorio implements Serializable{
    
    private static final long SerialVersionUID = 1L;
    
    //Identificador de la tabla Aspirante
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idDirectorio")
    private Long idDirectorio;
    
    //Relación con la base de datos; se realiza la notación:
    @ManyToOne(targetEntity = OfertaEducativa.class)
    @JoinColumn (name="idOfertaEducativa") //Puede que esto sea necesario o no
    OfertaEducativa ofertaEducativaDirectorio;
    
}
