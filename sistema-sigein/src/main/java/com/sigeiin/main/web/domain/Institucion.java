/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigeiin.main.web.domain;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;

/**
 *
 * @author Danie
 */
@Data
@Entity
@Table(name = "institucion")
public class Institucion implements Serializable{
    
    private static final long SerialVersionUID = 1L;
    
    //Identificador de la tabla Aspirante
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idInstitucion")
    private Long idInstitucion;
    
    @Column(name="nombreInstitucion")
    private String nombreInstitucion;
    
    @Column(name="acronimoInstitucion")
    
    private String acronimoInstitucion;
    @Column(name="logoInstitucion")
    
    private String logoInstitucion;
    @Column(name="razonInstitucional")
    
    private String razonInstitucional;
    @Column(name="facebookInstitucional")
    
    private String facebookInstitucional;
    @Column(name="youtubeInstitucional")
    private String youtubeInstitucional;
    
    @Column(name="latitudLocalizacion")
    private String latitudInstitucional;
    
    @Column(name="longitudLocalizacion")
    private String longitudInstitucional;
    
    @OneToMany(mappedBy="institucionInformacion")
    List<InformacionInstitucional>informacionInstitucion;
}
