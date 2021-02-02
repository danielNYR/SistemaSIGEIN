/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigeiin.main.web.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.sigeiin.main.web.domain.Valor;
import com.sigeiin.main.web.repository.ValorRepository;

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
public class valorController {
    
    @Autowired
    private ValorRepository serviceValor;
    //Variable de adjunto valor actual:
    private String adjunto_valor_actual = "";
    
    @GetMapping({"/admin/valores"})
    public String gestionValores(Model model){
        //log.info("El usuario ha entrado al controlador usuarios");
        model.addAttribute("valor",new Valor());
        model.addAttribute("listaValores", serviceValor.listarValores());
        return "adminvalores";
    }
    
    //Método que se encarga de el registro.
    @PostMapping({"/admin/valores/registrar"})
    public String registrarValor(Model model, Valor valor, @RequestParam("fileValor") MultipartFile adjunto){
        log.info("El id: "+valor.getIdValor());
        if(!adjunto.isEmpty()){
            log.info("Adjunto presente");
            if(valor.getIdValor() != null && valor.getAdjuntoValor() != null){
                log.info("-1");
                deleteFile(valor);
            }
            log.info(("1."));
            //Rutas del archivo del archivo que será subido
            String uniqueFileName = UUID.randomUUID().toString() + "_" + adjunto.getOriginalFilename();
            log.info(("2."));
            Path rootPath = Paths.get("uploads").resolve(uniqueFileName);
            log.info(("3."));
            Path rootAbsolutPath = rootPath.toAbsolutePath();
            log.info("A");
            try {
                log.info("B");
                Files.copy(adjunto.getInputStream(), rootAbsolutPath);
                log.info("C");
                valor.setAdjuntoValor(uniqueFileName);
                log.info("El archivo a subirse es: "+valor.getAdjuntoValor());
            } catch (IOException ex) {
                Logger.getLogger(modalidadController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        //Datos del registro
        if(valor.getIdValor() != null){
            log.info("Se está editand;");
            if(valor.getAdjuntoValor()==null ) {
            	//Se le asigna 
            	valor.setAdjuntoValor(adjunto_valor_actual);
            	log.info("Se ha asignado el adjunto:"+valor.getAdjuntoValor());
            }
            serviceValor.editarRegistro(valor);
        }else{
            log.info("se está registrando.");
            serviceValor.registerValor(valor); 
        }
        
        model.addAttribute("valor",new Valor());
        model.addAttribute("listaValores", serviceValor.listarValores());
        
        
        return "redirect:/admin/valores";
    }
    
    @RequestMapping(value ="/admin/valores/editar/{id}")
    public String editarValores(@PathVariable(value="id") Long id, Model model){
        
        Valor valor = null;
        
        if(id>0){
            valor = serviceValor.obtenerValor(id);
            //Se asigna el valor obtenido del adjunto para evitar que se convierta en un null
            adjunto_valor_actual = valor.getAdjuntoValor();
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
