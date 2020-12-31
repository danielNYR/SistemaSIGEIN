/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigeiin.main.web.controllers;

import com.sigeiin.main.web.dao.AreaInstitucionalDaoImplementation;
import com.sigeiin.main.web.domain.AreaInstitucional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Danie
 */
@Controller
@Slf4j //Anotación para poder incluir mensajes en la ejecución del sistema.
public class areaInstitucionalController{

    @Autowired
    @Qualifier("AreaInstitucionalDaoImplementationJPA")
    private AreaInstitucionalDaoImplementation serviceArea;
    
    @GetMapping({"/admin/area_institucional"})
    public String launchAreaInstitucional(Model model){
        log.info("Se ha desplegado la vista de area institucional");
        model.addAttribute("area", new AreaInstitucional());
        model.addAttribute("listaAreas", serviceArea.listarAreas());
        return "admareainstitucional";
    }
    
    @PostMapping({"/admin/area_institucional/registrar"})
    public String registrarAreaInstitucional(Model model, AreaInstitucional area){
        model.addAttribute("area", new AreaInstitucional());
        model.addAttribute("listaAreas", serviceArea.listarAreas());
        serviceArea.registrarArea(area);
        return "redirect:admareainstitucional";
    }
    
    @RequestMapping(value ="/admin/area_institucional/editar/{id}")
    public String editarAreaInstitucional(@PathVariable(value="id") Long id, Model model){
        
        AreaInstitucional area = null;
        
        if(id>0){
            area = serviceArea.encontrarArea(id);
        }else{
            //Aqui pondré el redirect para los modal de error
        }
        model.addAttribute("area", area);
        model.addAttribute("listaAreas", serviceArea.listarAreas());
        return "admareainstitucional";
    }
    
    @RequestMapping(value = "/admin/area_institucional/eliminar/{id}")
    public String eliminarAreaInstitucional(@PathVariable(value="id") Long id){
        if(id>0 || id!=null){
            serviceArea.eliminarArea(id);
        }
        return "redirect/admin/area_institucional";
    }
    
}
