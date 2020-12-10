/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigeiin.main.web.dao;

import com.sigeiin.main.web.domain.Noticia;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Danie
 */
@Repository("NoticiaRepositoryJPA")
public class NoticiaDaoImplementation implements iNoticiaDao {

    @PersistenceContext
    EntityManager em;
    
    //Agregaremos una etiqueta transaccional especificando el tipo de operación
    //Que tendrá permitido realizar
    @Transactional(readOnly = true)
    @Override
    public List<Noticia> listarNoticias() {
        return em.createQuery("from Noticia").getResultList(); //To change body of generated methods, choose Tools | Templates.
    }
    
}
