/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigeiin.main.web.dao;

import com.sigeiin.main.web.domain.AreaInstitucional;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Danie
 */
@Repository("AreaInstitucionalDaoImplementationJPA")
public class AreaInstitucionalDaoImplementation implements iAreaInstitucional{

    @PersistenceContext
    private EntityManager em; //Realiza consultas mediante JPA.
    
    //Agregaremos una etiqueta transaccional especificando el tipo de operación
    //Que tendrá permitido realizar
    @Transactional(readOnly = true)
    @Override
    public List<AreaInstitucional> listarAreas() {
        return em.createQuery("from AreaInstitucional").getResultList();
    }

    @Transactional
    @Override
    public void registrarArea(AreaInstitucional area) {
        if(area.getIdAreaInstitucional() != null && area.getIdAreaInstitucional() > 0){
            em.merge(area);
        }else{
           em.persist(area); 
        }
        
    }

    @Transactional
    @Override
    public AreaInstitucional encontrarArea(Long id) {
        return em.find(AreaInstitucional.class, id);
    }

    @Transactional
    @Override
    public void eliminarArea(Long id) {
        em.remove(encontrarArea(id));
    }

    
    
    
    
}
