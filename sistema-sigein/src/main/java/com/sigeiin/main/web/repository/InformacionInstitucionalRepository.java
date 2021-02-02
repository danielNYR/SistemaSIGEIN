/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigeiin.main.web.repository;

import javax.persistence.EntityManager;
import com.sigeiin.main.web.domain.InformacionInstitucional;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Danie
 */
@Repository("InformacionInstitucionalRepositoryJPA") //Etiqueta que nos indica que esta clase manejará datos. Ya que la
//Etiqueta como una clase de persistencia.Se le coloca un texto dentro de la 
//Etiqueta como buena práctica de programación y evitar ambiguedades en el futuro
//Así sabrá spring a cual llamar y a cual no.
public class InformacionInstitucionalRepository {

    @PersistenceContext
    private EntityManager em; //Realiza consultas mediante JPA.
    
    

    private Long idRegistro = 1L;
    InformacionInstitucional informacionIns = null;

    @Transactional
    public void registrarMision(InformacionInstitucional info) {
        if(info.getIdInformacion()!=null) {
        	em.merge(info);
        }else {
        	em.persist(info);
        }
    }

    @Transactional
   public void deleteInformacion(Long id) {
	   em.remove(obtenerInformacion(id));
   }

    @Transactional(readOnly=true)
    public InformacionInstitucional obtenerInformacion(Long id) {
        return em.find(InformacionInstitucional.class, id);
    }
    
    
}
