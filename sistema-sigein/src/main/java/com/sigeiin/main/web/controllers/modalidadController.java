/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigeiin.main.web.controllers;

import com.sigeiin.main.web.dao.ModalidadEducativaDaoImplementation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.sigeiin.main.web.domain.ModalidadEducativa;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Danie
 */
@Controller
@Slf4j //Anotación para poder incluir mensajes en la ejecución del sistema.
public class modalidadController {
    
    @Autowired
    @Qualifier("ModalidadEducativaDaoImplementationJPA")
    private ModalidadEducativaDaoImplementation serviceModalidad;
    
    
    @GetMapping({"/admin/modalidad",})
    public String gestionUsuarios(Model model){
        //log.info("El usuario ha entrado al controlador usuarios");
        model.addAttribute("modalidad", new ModalidadEducativa()); //Creación del objeto para utilizarlo a la hora de crear registros
        model.addAttribute("listaModalidad", serviceModalidad.listarOfertas()) ;
        return "admmodalidades";
    }
    
     @PostMapping({"admin/modalidad/registrar"})
    public String crearUsuario(Model modelo, ModalidadEducativa modalidad, @RequestParam("file") MultipartFile adjunto){
        //Código para el agregado de un archivo de tipo imagen
        if(!adjunto.isEmpty()){
            if(modalidad.getIdModalidadEducativa()>0){
                deleteFile(modalidad);
            }
            //Rutas del archivo del archivo que será subido
            String uniqueFileName = UUID.randomUUID().toString() + "_" + adjunto.getOriginalFilename();
            Path rootPath = Paths.get("uploads").resolve(uniqueFileName);
            Path rootAbsolutPath = rootPath.toAbsolutePath();
            
            try {
                
                Files.copy(adjunto.getInputStream(), rootAbsolutPath);
                
                modalidad.setAdjuntoModalidadEducativa(uniqueFileName);
                log.info("El archivo a subirse es: "+modalidad.getAdjuntoModalidadEducativa());
            } catch (IOException ex) {
                Logger.getLogger(modalidadController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        serviceModalidad.registrarModalidad(modalidad);
        modelo.addAttribute("modalidad", new ModalidadEducativa());
        modelo.addAttribute("listaModalidad", serviceModalidad.listarOfertas()) ;
        
        return "redirect:/admin/modalidad";
    }
    
    @RequestMapping(value ="/admin/modalidad/editar/{id}")
    public String editarModalidad(@PathVariable(value="id") Long id, Model model){
        
        ModalidadEducativa modalidad = null;
        
        if(id>0){
            modalidad = serviceModalidad.encontrarModalidad(id);
        }else{
            //Aqui pondré el redirect para los modal de error
        }
        model.addAttribute("modalidad", modalidad);
        model.addAttribute("listaModalidad", serviceModalidad.listarOfertas());
        return "admmodalidades";
    }
    
    //Método Eliminar
    @RequestMapping(value = "/admin/modalidad/eliminar/{id}")
    public String eliminar(@PathVariable(value="id") Long id){
        if(id>0){
            deleteFile(serviceModalidad.encontrarModalidad(id));
            serviceModalidad.eliminarModalidad(id);
        }
        return "redirect:/admin/modalidad";
    }
    
    //Método que elimina un archivo ya sea por que ha sido cambiado o por que el
    //source del registro ha sido eliminado
    private boolean deleteFile(ModalidadEducativa modalidad){
        //Obtenemos la ruta absoluta del archivo
        Path rootPath = Paths.get("uploads").resolve(modalidad.getAdjuntoModalidadEducativa()).toAbsolutePath();
        File archivo = rootPath.toFile();
        if(archivo.exists() && archivo.canRead()){
            return archivo.delete();
        }
        return false;
    }
}
