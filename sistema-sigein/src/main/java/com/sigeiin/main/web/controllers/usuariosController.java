/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigeiin.main.web.controllers;

import com.sigeiin.main.web.dao.AreaInstitucionalDaoImplementation;
import com.sigeiin.main.web.dao.UsuarioDaoImplementation;
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
public class usuariosController {
    @Autowired
    @Qualifier("UsuarioDaoImplementationJPA")
    private UsuarioDaoImplementation serviceUsuario; 
    
    @Autowired
    @Qualifier("AreaInstitucionalDaoImplementationJPA")
    private AreaInstitucionalDaoImplementation serviceAreaInstitucional;
    
    
    @GetMapping({"/admin/usuarios","/admin/gestion_usuarios", "/admin/geusuarios"})
    public String gestionUsuarios(Model model){
        //log.info("El usuario ha entrado al controlador usuarios");
        model.addAttribute("listaAreaInstitucional", serviceAreaInstitucional.listarAreas());
        model.addAttribute("listaUsuarios", serviceUsuario.listarUsuarios());
        return "usuariosadm";
    }
}
