/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigeiin.main.web.dao;

import com.sigeiin.main.web.domain.OfertaEducativa;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Danie
 */
@Repository("OfertaEducativaDaoImplementationJPA") //Etiqueta que nos indica que esta clase manejará datos. Ya que la
//Etiqueta como una clase de persistencia.Se le coloca un texto dentro de la 
//Etiqueta como buena práctica de programación y evitar ambiguedades en el futuro
//Así sabrá spring a cual llamar y a cual no.
public class OfertaEducativaDaoImplementation implements iOfertaEducativaDao{

    //Importamos el EntityManager: Sirve para distintas cosas, principalmente 
    //Para controlar el acceso a datos, ciclo de vida, ya que realiza todas las
    //Operaciones con la base de datos a nivel de objeto.
    @PersistenceContext
    private EntityManager em; //Realiza consultas mediante JPA.
    
    //Agregaremos una etiqueta transaccional especificando el tipo de operación
    //Que tendrá permitido realizar
    @Transactional(readOnly = true)
    public List<OfertaEducativa> listarOfertas() {
        return em.createQuery("from OfertaEducativa").getResultList();
    }

    @Transactional
    @Override
    public void registrarOferta(OfertaEducativa oferta) {
        if(oferta.getIdOfertaEducativa() != null && oferta.getIdOfertaEducativa() > 0){
            em.merge(oferta);
        }else{
           em.persist(oferta); 
        }
        
    }
    
    @Override
    @Transactional(readOnly = true)
    public OfertaEducativa encontrarOferta(Long id) {
        return em.find(OfertaEducativa.class, id);
    }
    
    
    @Override
    @Transactional
    public void eliminarOferta(Long id) {
        em.remove(encontrarOferta(id)); //Permite encontrar id y luego eliminarlo
        
    }

    
}
