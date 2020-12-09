/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigeiin.main.web.domain;

import com.sigeiin.main.web.domain.OfertaEducativa;
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
 * @author Danie Nava Revolledo
 */
@Data
@Entity
@Table(name = "aspirante")
public class Aspirante implements Serializable{

    private static final long SerialVersionUID = 1L;
    
    //Identificador de la tabla Aspirante
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAspirante;
    
    //Componentes adicionales de la tabla Aspirante
    @Column(name="nombreAspirante")
    private String nombreAspirante;
    @Column(name="ApellidoPaternoAspirante")
    private String apellidoPaternoAspirante;
    @Column(name="ApellidoMaternoAspirante")
    private String apellidoMaternoAspirante;
    @Column(name="EmailAspirante")
    private String emailAspirante;
    //Relación con la base de datos; se realiza la notación:
    @ManyToOne(targetEntity = OfertaEducativa.class)
    @JoinColumn (name="idOfertaEducativa") //Puede que esto sea necesario o no
    OfertaEducativa ofertaEducativa;
    
    

    public Long getIdAspirante() {
        return idAspirante;
    }

    public void setIdAspirante(Long idAspirante) {
        this.idAspirante = idAspirante;
    }

    public Aspirante() {
    }


    
    public Aspirante(Long idAspirante, String nombreAspirante, String apellidoPaternoAspirante, String apellidoMaternoAspirante, String emailAspirante) {
        this.idAspirante = idAspirante;
        this.nombreAspirante = nombreAspirante;
        this.apellidoPaternoAspirante = apellidoPaternoAspirante;
        this.apellidoMaternoAspirante = apellidoMaternoAspirante;
        this.emailAspirante = emailAspirante;
    }
    
    
  
    
    
    
}
