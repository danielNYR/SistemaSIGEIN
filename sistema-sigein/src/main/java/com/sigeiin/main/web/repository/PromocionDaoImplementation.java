/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigeiin.main.web.repository;

import com.sigeiin.main.web.dao.iPromocionDao;
import com.sigeiin.main.web.domain.Promocion;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Danie
 */
@Repository("PromocionDaoImplementationJPA")
public class PromocionDaoImplementation implements iPromocionDao{

    @PersistenceContext
    private EntityManager em; //Realiza consultas mediante JPA.
    
    //Agregaremos una etiqueta transaccional especificando el tipo de operación
    //Que tendrá permitido realizar
    @Transactional(readOnly = true)
    @Override
    public List<Promocion> listarPromociones() {
        return em.createQuery("from Promocion").getResultList();
    }

    @Transactional
    @Override
    public void registrarPromocion(Promocion promocion) {
        em.persist(promocion);
    }
    
   
    
}
