/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigeiin.main.web.controllers;

import com.sigeiin.main.web.dao.NoticiaDaoImplementation;
import com.sigeiin.main.web.dao.iAspiranteDao;
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
public class noticiaController {
    
    @Autowired
    @Qualifier("NoticiaRepositoryJPA")
    private NoticiaDaoImplementation serviceNoticia;
    
    //Gestionar a los aspirantes
    @Autowired
    @Qualifier("OfertaEducativaDaoImplementationJPA")
    private iOfertaEducativaDao serviceOfertaEducativa;
    
    
    @GetMapping({"/admin/noticias", "/admin/gnoticias"})
    public String gestionNoticia(Model model){
        log.info("El usuario ha entrado al controlador Gestion Noticia");
        model.addAttribute("listaAreas", serviceOfertaEducativa.listarOfertas());
        model.addAttribute("listaNoticia", serviceNoticia.listarNoticias());
        return "admnoticias";
    }
}
