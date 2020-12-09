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
@Data
@Entity
@Table(name = "noticia")
public class Noticia implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idNoticia;

    @Column
    private String FechaEmisionNoticia;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "detallenoticia",
            joinColumns = @JoinColumn(name = "idNoticia"),
            inverseJoinColumns = @JoinColumn(name = "idAreaInstitucional"))
    private Set<AreaInstitucional> areas_institucionales;
    
}
