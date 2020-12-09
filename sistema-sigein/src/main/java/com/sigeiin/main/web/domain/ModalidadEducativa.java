/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigeiin.main.web.domain;

import java.io.Serializable;
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

/**
 *
 * @author Danie
 */
@Entity
@Data
@Table (name = "ModalidadEducativa")
public class ModalidadEducativa implements Serializable{
    
    private static final long SerialVersionUID = 1L;
    
    //Identificador de la tabla ModalidadEducativa
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idModalidadEducativa;
    
    @Column(name = "NombreModalidadEducativa")
    private String nombreModalidadEducativa;
    @Column(name="PeriodoModalidadEducativa")
    private String periodoModalidadEducativa;
    @Column(name="DescripcionModalidadEducativa")
    private String descripcionModalidadEducativa;
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "detalleOfertaEducativa",
            joinColumns = @JoinColumn(name = "idModalidadEducativa"),
            inverseJoinColumns = @JoinColumn(name = "idOfertaEducativa"))
    private Set<ModalidadEducativa> ofertasEducativas;
    
}
