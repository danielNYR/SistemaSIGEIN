/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigeiin.main.web.repository;

import java.util.List;
import com.sigeiin.main.web.domain.Valor;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Danie
 */
@Repository("ValorRepositoryJPA") //Etiqueta que nos indica que esta clase manejará datos. Ya que la
//Etiqueta como una clase de persistencia.Se le coloca un texto dentro de la 
//Etiqueta como buena práctica de programación y evitar ambiguedades en el futuro
//Así sabrá spring a cual llamar y a cual no.
public class ValorRepository {
    //Importamos el EntityManager: Sirve para distintas cosas, principalmente 
    //Para controlar el acceso a datos, ciclo de vida, ya que realiza todas las
    //Operaciones con la base de datos a nivel de objeto.
    @PersistenceContext
    private EntityManager em; //Realiza consultas mediante JPA.
    
    @Transactional(readOnly = true)
    public List<Valor> listarValores(){
        return em.createQuery("from Valor").getResultList();
    }
    
    @Transactional(readOnly = true)
    public Valor obtenerValor(Long id){
        return em.find(Valor.class, id);
    }
    
    @Transactional
    public void registerValor(Valor valor){
            em.persist(valor);
        
    }
    
    @Transactional
    public void editarRegistro(Valor valor){
        em.merge(valor);
    }
    
   @Transactional
   public void deleteValor(Long id){
       em.remove(obtenerValor(id));
   }
}
