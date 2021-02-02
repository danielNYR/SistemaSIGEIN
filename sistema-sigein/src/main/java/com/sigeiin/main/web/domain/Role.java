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
import javax.persistence.Table;
import lombok.Data;

/**
 *
 * @author Danie
 */
@Entity
@Data
@Table (name = "Role")
public class Role implements Serializable {
    
    private static final long SerialVersionUID = 1L;
    
    @Id
    @Column(name="idRole")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRole;
    
    @Column(name="nombreRole")
    private String nombreRole;
}
