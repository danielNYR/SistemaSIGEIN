/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigeiin.main.web.repository;

import com.sigeiin.main.web.dao.iUsuario;
import com.sigeiin.main.web.domain.Aspirante;
import com.sigeiin.main.web.domain.Usuario;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Danie
 */
@Repository("UsuarioDaoImplementationJPA")
public class UsuarioDaoImplementation implements iUsuario{

    //Importamos el EntityManager: Sirve para distintas cosas, principalmente 
    //Para controlar el acceso a datos, ciclo de vida, ya que realiza todas las
    //Operaciones con la base de datos a nivel de objeto.
    @PersistenceContext
    private EntityManager em; //Realiza consultas mediante JPA.
    
    //Agregaremos una etiqueta transaccional especificando el tipo de operación
    //Que tendrá permitido realizar
    @Transactional(readOnly = true)
    @Override
    public List<Usuario> listarUsuarios() {
        return em.createQuery("from Usuario").getResultList();
    }

    @Transactional
    @Override
    public void agregarUsuario(Usuario usuario) {
        if(usuario.getIdUsuario() != null && usuario.getIdUsuario()>0){
            em.merge(usuario);
        }else{
           em.persist(usuario); 
        }
        
    }

    @Transactional
    @Override
    public Usuario encontrarUsuario(Long id) {
        return em.find(Usuario.class, id);
    }

    @Transactional
    @Override
    public void eliminarUsuario(Long id) {
        em.remove(encontrarUsuario(id));
    }

    
}
