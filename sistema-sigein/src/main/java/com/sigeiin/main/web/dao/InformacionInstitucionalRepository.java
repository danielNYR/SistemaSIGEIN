/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigeiin.main.web.dao;

import javax.persistence.EntityManager;
import com.sigeiin.main.web.domain.InformacionInstitucional;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

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

    public void registrarMision(String mision, String file) {

        if (mision != "" || mision != null) {
            informacionIns = obtenerInformacion();
            if (informacionIns != null) {
                informacionIns.setInformacionMision(mision);
                if (file != "" && file != null) {
                    informacionIns.setAdjuntoVision(mision);
                }
                em.merge(informacionIns);
            }else{
                //No se ha hecho el primer registro, no se puede continuar
            }

        }
    }

    public void registrarVision(String vision, String file) {
        if (vision != "" || vision != null) {
            informacionIns = obtenerInformacion();
            informacionIns.setInformacionVision(vision);
            if (file != "" && file != null) {
                informacionIns.setAdjuntoVision(vision);
            }
            em.merge(informacionIns);
        }
    }

    public InformacionInstitucional obtenerInformacion() {
        return em.find(InformacionInstitucional.class, idRegistro);
    }
    
    
}
