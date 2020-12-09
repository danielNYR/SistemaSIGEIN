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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.MappedSuperclass;
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
    
    //Componentes adicionales de la tabla: 
    @Column(name = "TituloOfertaEducativa")
    private String TituloOfertaEducativa;
    
    @Column(name = "ObjetivoGeneralOfertaEducativa")
    private String ObjetivoGeneralOfertaEducativa;
    
    @Column(name = "AcercaDeOfertaEducativa")
    private String AcercaDeOfertaEducativa;
    
    @Column(name ="ReticulaOfertaEducativa")
    private String ReticulaOfertaEducativa;
    
    @Column(name ="PlanEstudiosOfertaEducativa")
    private String PlanEstudiosOfertaEducativa;
    
    @Column(name ="AdjuntoOfertaEducativa")
    private String AdjuntoOfertaEducativa;
    
    @Column(name ="AdjuntoObjetivoOfertaEducativa")
    private String AdjuntoObjetivoOfertaEducativa;
    
    @Column(name = "AdjuntoAcercaOfertaEducativa")
    private String AdjuntoAcercaOfertaEducativa;
    
    //Relación resultante del many to many
    @ManyToMany(fetch = FetchType.LAZY, targetEntity = Promocion.class)
    @JoinTable(name = "DetallePromocion",
            joinColumns = @JoinColumn(name = "idOfertaEducativa"),
            inverseJoinColumns = @JoinColumn(name = "idPromocion"))
    private Set<OfertaEducativa> detallePromocion;
    

}
