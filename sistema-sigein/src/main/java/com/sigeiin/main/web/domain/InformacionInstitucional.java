/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigeiin.main.web.domain;

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
@Table(name = "informacioninstitucional")
public class InformacionInstitucional {

    private static final long SerialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idInformacion")
    private Long idInformacion;
    
    @Column(name = "InformacionVision")
    private String informacionVision;
    
    @Column(name = "AdjuntoVision")
    private String adjuntoVision;
    
    @Column(name = "InformacionMision")
    private String informacionMision;
    
    @Column(name = "AdjuntoMision")
    private String adjuntoMision;
    
    @ManyToOne(targetEntity = Institucion.class)
    @JoinColumn (name="idInstitucion") //Puede que esto sea necesario o no
    Institucion institucionInformacion;

    
    
}
