/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigeiin.main.web.controllers;

import com.sigeiin.main.web.domain.Noticia;
import com.sigeiin.main.web.repository.AreaInstitucionalDaoImplementation;
import com.sigeiin.main.web.repository.NoticiaDaoImplementation;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

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
    @Qualifier("AreaInstitucionalDaoImplementationJPA")
    private AreaInstitucionalDaoImplementation serviceAreaEducativa;
    
    
    @GetMapping({"/admin/noticias", "/admin/gnoticias"})
    public String gestionNoticia(Model model){
        log.info("El usuario ha entrado al controlador Gestion Noticia");
        model.addAttribute("noticia", new Noticia());
        model.addAttribute("listaAreas", serviceAreaEducativa.listarAreas());
        model.addAttribute("listaNoticia", serviceNoticia.listarNoticias());
        return "admnoticias";
    }
    
    @PostMapping({"admin/noticias/registrar"})
    public String crearNoticia(Model modelo, Noticia noticia, @RequestParam("file01") MultipartFile adjunto){
        //Código para el agregado de un archivo de tipo imagen
        if(!adjunto.isEmpty()){
            //Path directorioRecursos = Paths.get("src//main//resources//static/updloads"); //Carpeta en la que se guardará el archivo
            //Nuevo PATH C:\Users\Danie\Documents\proyecto_sigeins/uploads
            //Path directorioRecursos = Paths.get("C://Users//Danie//Documents//proyecto_sigeins//uploads");
            //Para evitar tener archivos con el mismo nombre y no se sobre escriban
            String uniqueFileName = "notice/"+UUID.randomUUID().toString() + "_" + adjunto.getOriginalFilename();
            Path rootPath = Paths.get("uploads").resolve(uniqueFileName);
            Path rootAbsolutPath = rootPath.toAbsolutePath();
            log.info("root: " +rootPath);
            log.info("absolute: "+rootAbsolutPath);
            try {
                Files.copy(adjunto.getInputStream(), rootAbsolutPath);
                
                noticia.setAdjuntoNoticia(uniqueFileName);
                
                log.info("El archivo a subirse es: "+noticia.getAdjuntoNoticia());
            } catch (IOException ex) {
                log.info("error: "+ex);
                Logger.getLogger(modalidadController.class.getName()).log(Level.SEVERE, null, ex);
            }
    	log.info("dale");
        }
        
        modelo.addAttribute("noticia", new Noticia());
        modelo.addAttribute("listaAreas", serviceAreaEducativa.listarAreas());
        modelo.addAttribute("listaNoticia", serviceNoticia.listarNoticias()) ;
        serviceNoticia.registrarNoticia(noticia);
        return "redirect:noticias";
    }
}
