/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigeiin.main.web.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author Danie
 */
@Controller
@Slf4j //Anotación para poder incluir mensajes en la ejecución del sistema.
public class noticiaController {
    @GetMapping({"/admin/noticias", "/admin/gnoticias"})
    public String gestionNoticia(){
        //log.info("El usuario ha entrado al controlador aspirante");
        return "admnoticias";
    }
}
