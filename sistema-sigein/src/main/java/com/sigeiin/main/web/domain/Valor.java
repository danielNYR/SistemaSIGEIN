package com.sigeiin.main.web.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table (name = "valor")
public class Valor implements Serializable{
    
    private static final long SerialVersionUID = 1L;
    
    @Id
    @Column(name="idValor")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idValor;
    
    @Column(name="tituloValor")
    private String tituloValor;
    
    @Column(name="descripcionValor")
    private String descripcionValor;
    
    @Column(name="adjuntoValor")
    private String adjuntoValor;
    
}