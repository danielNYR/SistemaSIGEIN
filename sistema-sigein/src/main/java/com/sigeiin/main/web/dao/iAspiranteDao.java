/*
 *Modelos de clase aspirante, es donde se escribirán los métodos obligatorios
para poder llevar a cabo operaciones de base de datos mediante el framework
 */
package com.sigeiin.main.web.dao;

import com.sigeiin.main.web.domain.Aspirante;
import java.util.List;

/**
 *
 * @author Daniel Nava Revolledo
 */
public interface iAspiranteDao {
    //Método FindAll: Se prevee que haga solicitud a la base de datos solicitand
    //Información y devolviéndola al controlador en forma de ArrayList
    //Aspirante objetoAspirante;
    public List<Aspirante> listarAspirantes();
    public void registrarAspirante(Aspirante aspirante);
    public Aspirante encontrarAspirante(Long id);
    public void eliminarAspirante(Long id);
    
}
