/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigeiin.main.web.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author Danie
 */
@Data
@Entity
@Table (name="promocion")
public class Promocion implements Serializable{
    
    private static final long SerialVersionUID = 1L;
    
    //Identificador de la tabla Aspirante
    @Id
    @Column(name="idPromocion")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPromocion;
    @Column(name="TituloPromocion")
    private String tituloPromocion;
    @Column(name="FechaInicio")
    //@DateTimeFormat
    private Date fechaInicio;
    @Column(name="FechaFinal")
    //@DateTimeFormat
    private Date fechaFinal;
    @Column(name="Descripcion")
    private String descripcion;
    @Column(name="PorcentajePromocion")
    private int porcentajePromocion;
    @Column(name="AdjuntoPromocion")
    private String adjuntoPromocion;
    //Aquí va la unión ManyToMany
    @ManyToMany(mappedBy = "detallePromocion")
    Set <OfertaEducativa>ofertasEducativas;
    
    
    
}
