/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigeiin.main.web.repository;

import com.sigeiin.main.web.dao.iModalidadEducativa;
import com.sigeiin.main.web.domain.Aspirante;
import com.sigeiin.main.web.domain.ModalidadEducativa;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Danie
 */
@Repository("ModalidadEducativaDaoImplementationJPA") //Etiqueta que nos indica que esta clase manejará datos. Ya que la
//Etiqueta como una clase de persistencia.Se le coloca un texto dentro de la 
//Etiqueta como buena práctica de programación y evitar ambiguedades en el futuro
//Así sabrá spring a cual llamar y a cual no.
public class ModalidadEducativaDaoImplementation implements iModalidadEducativa{
    //Importamos el EntityManager: Sirve para distintas cosas, principalmente 
    //Para controlar el acceso a datos, ciclo de vida, ya que realiza todas las
    //Operaciones con la base de datos a nivel de objeto.
    @PersistenceContext
    private EntityManager em; //Realiza consultas mediante JPA.
    
    //Agregaremos una etiqueta transaccional especificando el tipo de operación
    //Que tendrá permitido realizar
    @Transactional(readOnly = true)
    @Override
    public List<ModalidadEducativa> listarOfertas() {
         //To change body of generated methods, choose Tools | Templates.
         return em.createQuery("from ModalidadEducativa").getResultList();
    }

    @Transactional
    @Override
    public void registrarModalidad(ModalidadEducativa modalidad) {
        if(modalidad.getIdModalidadEducativa() != null && modalidad.getIdModalidadEducativa() > 0){
            em.merge(modalidad);
        }else{
            em.persist(modalidad); 
        }
        
    }

    @Override
    @Transactional(readOnly = true)
    public ModalidadEducativa encontrarModalidad(Long id) {
        return em.find(ModalidadEducativa.class, id);
    }

    @Override
    @Transactional
    public void eliminarModalidad(Long id) {
        em.remove(encontrarModalidad(id));
    }
   
}
