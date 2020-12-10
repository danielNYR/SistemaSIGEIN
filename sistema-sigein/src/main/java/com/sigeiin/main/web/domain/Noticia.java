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
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;

/**
 *
 * @author Danie
 */
@Data
@Entity
@Table(name = "Noticia")
public class Noticia implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idNoticia;
    
    @Column(name="TituloNoticia")
    private String tituloNoticia;
    
    @Column(name="FechaEmisionNoticia")
    private String fechaEmisionNoticia;
    
    @Column(name = "ContenidoNoticia")
    private String contenidoNoticia;
    
    @Column(name="AdjuntoNoticia")
    private String adjuntoNoticia;
    

    //Debido a que se trata de una institución educativa, el autor o considerado
    //Autor de una noticia es el nombre del área institucional que la emite.
    @ManyToOne(targetEntity = AreaInstitucional.class)
    @JoinColumn(name="idAreaInstitucional")
    AreaInstitucional areaInstitucionalNoticia;
    
}
