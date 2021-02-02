/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigeiin.main.web.controllers;

import com.sigeiin.main.web.domain.Usuario;
import com.sigeiin.main.web.repository.AreaInstitucionalDaoImplementation;
import com.sigeiin.main.web.repository.UsuarioDaoImplementation;

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
public class usuariosController {
    @Autowired
    @Qualifier("UsuarioDaoImplementationJPA")
    private UsuarioDaoImplementation serviceUsuario; 
    
    @Autowired
    @Qualifier("AreaInstitucionalDaoImplementationJPA")
    private AreaInstitucionalDaoImplementation serviceAreaInstitucional;
    
    
    @GetMapping({"/admin/usuarios","/admin/usuarios/usuariosadm", "/admin/geusuarios"})
    public String gestionUsuarios(Model model){
        //log.info("El usuario ha entrado al controlador usuarios");
        model.addAttribute("usuario",new Usuario());
        model.addAttribute("listaAreaInstitucional", serviceAreaInstitucional.listarAreas());
        model.addAttribute("listaUsuarios", serviceUsuario.listarUsuarios());
        return "usuariosadm";
    }
    
    
    @PostMapping({"admin/usuarios/registrar"})
    public String crearUsuario(Model modelo, Usuario usuario){
        
        modelo.addAttribute("usuario", new Usuario());
        modelo.addAttribute("listaAreaInstitucional", serviceAreaInstitucional.listarAreas());
        modelo.addAttribute("listaUsuarios", serviceUsuario.listarUsuarios());
        serviceUsuario.agregarUsuario(usuario);
        return "redirect:/admin/usuarios";
    }
    
    @RequestMapping(value ="/admin/usuarios/editar/{id}")
    public String editarAreaInstitucional(@PathVariable(value="id") Long id, Model model){
        
        Usuario usuario = null;
        
        if(id>0){
            usuario = serviceUsuario.encontrarUsuario(id);
        }else{
            //Aqui pondré el redirect para los modal de error
        }
        model.addAttribute("usuario", usuario);
        model.addAttribute("listaAreaInstitucional", serviceAreaInstitucional.listarAreas());
        model.addAttribute("listaUsuarios", serviceUsuario.listarUsuarios());
        return "usuariosadm";
    }
    
    @RequestMapping(value = "/admin/usuarios/eliminar/{id}")
    public String eliminarAreaInstitucional(@PathVariable(value="id") Long id){
        if(id>0 || id!=null){
            serviceUsuario.eliminarUsuario(id);
        }
        return "redirect:/admin/usuarios";
    }
    
    /*
    @Request @PostMapping({"admin/usuarios/registrar"})
    public String crearUsuario(Model modelo, Usuario usuario){
        
        modelo.addAttribute("usuario", new Usuario());
        modelo.addAttribute("listaAreaInstitucional", serviceAreaInstitucional.listarAreas());
        modelo.addAttribute("listaUsuarios", serviceUsuario.listarUsuarios());
        serviceUsuario.agregarUsuario(usuario);
        return "redirect:usuariosadm";
    }Mapping(value="/admin/usuarios")
    public String crear(Map<String, Object>model){
        Usuario usuario = new Usuario();
        model.put("usuario", usuario);
        return "usuariosadm";
    }
    
    @RequestMapping(value="/admin/usuarios/registrar", method=RequestMethod.POST)
    public String guardar(Usuario usuario){
        
        serviceUsuario.agregarUsuario(usuario);
        return "redirect::usuariosadm";
    }
*/
}
