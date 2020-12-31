/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigeiin.main.web.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;
import javax.persistence.Table;

/**
 *
 * @author Danie
 */
@Data
@Entity
@Table(name="valor")
public class Valor implements Serializable {

    private static final long SerialVersionUID = 1L;
    
    @Id
    @Column(name ="idValor")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idValor;
    
    
    @Column(name ="tituloValor")
    private String tituloValor;
    @Column(name="descripcionValor")
    private String descripcionValor;
    @Column(name="adjuntoValor")
    private String adjuntoValor;

    public Long getId() {
        return idValor;
    }

    public void setId(Long id) {
        this.idValor = id;
    }
 
    //Ver notaciones para JPA donde el valor del id ya esté asignado previamente
    //Como será el caso de este, ya que sólo se registrarán valores institucionales
    //Dicho apartado institucional
    //********Corrregir la base de datos en la tabla que entra en conflicto con
    //Esta parte, ya que no se maneja el título de los valores, así como tampoco
    //Se están manejando otras variables que serán necesarias y la tabla apartado
    //Termina estando de más. 
    //Crearé una tabla llamada institución, la cual estará relacionada con la tabla
    //Valores institucionales
    //tendrán una relación sin sentido ya que solo existirá una institución. jaja
    
}
