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
@Entity
@Data
@Table (name = "AreaInstitucional")
public class AreaInstitucional implements Serializable{

    @Id
    @Column(name="idAreaInstitucional")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAreaInstitucional;
    
    //Relaciones de la tabla AreaInstitucional con la tabla Usuarios
    @OneToMany(mappedBy="areaInstitucional")
    List<Usuario> areaUsuarios;
    //Relaciones de la tabla Noticias del area Institucional
    @OneToMany(mappedBy="areaInstitucionalNoticia")
    List<Noticia>noticasAreaInstitucional;
    
    //Columnas de la tabla area institucional
    @Column(name ="TituloAreaInstitucional")
    private String tituloAreaInstitucional;
    
    @Column(name="DescripcionAreaInstitucional")
    private String descripcionAreaInstitucional;
    

    public Long getIdAreaInstitucional() {
        return idAreaInstitucional;
    }

    public void setIdAreaInstitucional(Long idAreaInstitucional) {
        this.idAreaInstitucional = idAreaInstitucional;
    }
    
    
    
}
