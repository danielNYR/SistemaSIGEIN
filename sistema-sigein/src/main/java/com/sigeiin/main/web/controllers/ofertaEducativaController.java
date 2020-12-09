/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigeiin.main.web.controllers;

import com.sigeiin.main.web.dao.iOfertaEducativaDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author Danie
 */
@Controller
@Slf4j //Anotación para poder incluir mensajes en la ejecución del sistema.
public class ofertaEducativaController {
    
    @Autowired
    @Qualifier("OfertaEducativaDaoImplementationJPA")
    private iOfertaEducativaDao service_OfertaEducativa;
    
    @GetMapping({"/admin/ofeducativa","/admin/oferta", "/admin/oferta_educativa"})
    public String gestionNoticia(Model model){
        model.addAttribute("listaOferta", service_OfertaEducativa.listarOfertas()); //Con esto hacemos un llamado a todos los datos en la base de datos que se obtuvieron
        //log.info("El usuario ha entrado al controlador aspirante");
        return "ofeducativa";
    }
}
