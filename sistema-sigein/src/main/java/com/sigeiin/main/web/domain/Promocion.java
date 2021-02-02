/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigeiin.main.web.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
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
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;

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
    private String fechaInicio;
    
    @Column(name="FechaFinal")
    private String fechaFinal;
    
    @Column(name="Descripcion")
    private String descripcion;
    
    @Column(name="PorcentajePromocion")
    private int porcentajePromocion;
    
    @Column(name="AdjuntoPromocion")
    private String adjuntoPromocion;
    //Aquí va la unión ManyToMany
    
    @ManyToOne(targetEntity = AreaInstitucional.class)
    @JoinColumn (name="idAreaInstitucional")
    AreaInstitucional areaInstitucionalPromo;
    /*
    @ManyToMany(fetch = FetchType.LAZY, targetEntity = OfertaEducativa.class)
    @JoinTable(name = "detallepromocion",
            joinColumns = @JoinColumn(name = "idPromocion"),
            inverseJoinColumns = @JoinColumn(name = "idOfertaEducativa"))
    private Set<OfertaEducativa> ofertasEducativas;
    */
    
    
    
    
}
