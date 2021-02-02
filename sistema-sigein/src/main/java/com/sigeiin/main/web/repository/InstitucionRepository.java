/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigeiin.main.web.repository;

import com.sigeiin.main.web.domain.Institucion;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Danie
 */
@Repository("InstitucionRepositoryJPA") //Etiqueta que nos indica que esta clase manejará datos. Ya que la
//Etiqueta como una clase de persistencia.Se le coloca un texto dentro de la 
//Etiqueta como buena práctica de programación y evitar ambiguedades en el futuro
//Así sabrá spring a cual llamar y a cual no.
public class InstitucionRepository {
     //Importamos el EntityManager: Sirve para distintas cosas, principalmente 
    //Para controlar el acceso a datos, ciclo de vida, ya que realiza todas las
    //Operaciones con la base de datos a nivel de objeto.
    @PersistenceContext
    private EntityManager em; //Realiza consultas mediante JPA.
    
    @Transactional(readOnly = true)
    public List<Institucion> listarInstitucion(){
        return em.createQuery("from Institucion").getResultList();
    }
    
    @Transactional(readOnly = true)
    public Institucion obtenerInstitucion(Long id){
        return em.find(Institucion.class, id);
    }
    
    @Transactional
    public void registerInstitucion(Institucion institucion){
        if(institucion.getIdInstitucion() != null){ //Si el id ya existe
            em.merge(institucion);
        }else{ //Si el id no viene con el objeto, por lo cual es nuevo
            em.persist(institucion);
        }
    }
    //No existe el método eliminar, puesto que la institución es la raíz del sistema sigein.
    //Por lo cual no se puede eliminar
}
