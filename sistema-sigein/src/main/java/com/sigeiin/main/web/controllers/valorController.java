/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigeiin.main.web.controllers;

import com.sigeiin.main.web.dao.ValorRepository;
import com.sigeiin.main.web.domain.Valor;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
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
public class valorController {
    
    @Autowired
    @Qualifier("ValorRepositoryJPA")
    private ValorRepository serviceValor;
    
    @GetMapping({"/admin/valores"})
    public String gestionValores(Model model){
        //log.info("El usuario ha entrado al controlador usuarios");
        model.addAttribute("valor",new Valor());
        model.addAttribute("listaValores", serviceValor.listarValores());
        return "adminvalores";
    }
    
    @PostMapping({"/admin/valores/registrar"})
    public String registrarValor(Model model, Valor valor){
        model.addAttribute("valor",new Valor());
        model.addAttribute("listaValores", serviceValor.listarValores());
        if(valor != null){
            serviceValor.registerValor(valor);
        }
        
        return "redirect:/admin/valores";
    }
    
    @RequestMapping(value ="/admin/valores/editar/{id}")
    public String editarValores(@PathVariable(value="id") Long id, Model model){
        
        Valor valor = null;
        
        if(id>0){
            valor = serviceValor.obtenerValor(id);
        }else{
            //Aqui pondré el redirect para los modal de error
        }
        model.addAttribute("valor", valor);
        model.addAttribute("listaValores", serviceValor.listarValores());
        return "adminvalores";
    }
    
    @RequestMapping(value = "/admin/valores/eliminar/{id}")
    public String eliminarValores(@PathVariable(value="id") Long id){
        if(id>0 || id!=null){
            serviceValor.deleteValor(id);
            deleteFile(serviceValor.obtenerValor(id));
        }
        return "redirect:/admin/valores";
    }
    
    //Método que elimina un archivo ya sea por que ha sido cambiado o por que el
    //source del registro ha sido eliminado
    private boolean deleteFile(Valor valor){
        //Obtenemos la ruta absoluta del archivo
        Path rootPath = Paths.get("uploads").resolve(valor.getAdjuntoValor()).toAbsolutePath();
        File archivo = rootPath.toFile();
        if(archivo.exists() && archivo.canRead()){
            return archivo.delete();
        }
        return false;
    }
}
