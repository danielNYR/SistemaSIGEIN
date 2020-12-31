/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigeiin.main.web.domain;

import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table( name = "OfertaEducativa")
public class OfertaEducativa {
    
    private static final long SerialVersionUID = 1L;
    
    @Id
    @Column(name ="idOfertaEducativa")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idOfertaEducativa;
    
    @OneToMany(mappedBy = "ofertaEducativa")
    private List<Aspirante> aspirantes;
    
    @OneToMany(mappedBy = "ofertaEducativaDirectorio")
    private List<Directorio> directorio;
    
    //Componentes adicionales de la tabla: 
    @Column(name = "TituloOfertaEducativa")
    private String tituloOfertaEducativa;
    
    @Column(name = "ObjetivoGeneralOfertaEducativa")
    private String objetivoGeneralOfertaEducativa;
    
    @Column(name = "AcercaDeOfertaEducativa")
    private String acercaDeOfertaEducativa;
    
    @Column(name ="ReticulaOfertaEducativa")
    private String reticulaOfertaEducativa;
    
    @Column(name ="PlanEstudiosOfertaEducativa")
    private String planEstudiosOfertaEducativa;
    
    @Column(name ="AdjuntoOfertaEducativa")
    private String adjuntoOfertaEducativa;
    
    @Column(name ="AdjuntoObjetivoOfertaEducativa")
    private String adjuntoObjetivoOfertaEducativa;
    
    @Column(name = "AdjuntoAcercaOfertaEducativa")
    private String adjuntoAcercaOfertaEducativa;
    
    
    

}
