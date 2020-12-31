/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigeiin.main.web.controllers;

import com.sigeiin.main.web.dao.AspiranteDaoImplementation;
import com.sigeiin.main.web.dao.iAspiranteDao;
import com.sigeiin.main.web.dao.iOfertaEducativaDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Danie
 */
@Controller
@Slf4j //Anotación para poder incluir mensajes en la ejecución del sistema.
public class aspiranteController {
    
    @Autowired
    @Qualifier("AspiranteDaoImplementationJPA")
    private AspiranteDaoImplementation serviceAspirante; 

    //Llamamos al de la oferta educativa, ya que se manejan ambos para poder
    //Gestionar a los aspirantes
    @Autowired
    @Qualifier("OfertaEducativaDaoImplementationJPA")
    private iOfertaEducativaDao serviceOfertaEducativa;
    //Inicia el controlador y solicita mediante el método Get Mapping
    @GetMapping({"/admin/aspirante", "/admin/gaspirante"})
    public String listar(Model model){
        //var aspirantes = serviceAspirante.listarAspirantes();
        //var ofertaeducativa = serviceOfertaEducativa.listarOfertas();
        
        model.addAttribute("listaOferta", serviceOfertaEducativa.listarOfertas());
        model.addAttribute("listaAspirantes", serviceAspirante.listarAspirantes());
        return "admaspirantes";
    }
    
    //Método Eliminar
    @RequestMapping(value = "/admin/aspirante/eliminar/{id}")
    public String eliminar(@PathVariable(value="id") Long id){
        if(id>0){
            serviceAspirante.eliminarAspirante(id);
        }
        return "redirect:/admin/aspirante";
    }
    
}
