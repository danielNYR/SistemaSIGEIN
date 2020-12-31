/*


 * 
 * 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigeiin.main.web.controllers;

import com.sigeiin.main.web.dao.PromocionDaoImplementation;
import com.sigeiin.main.web.dao.iOfertaEducativaDao;
import com.sigeiin.main.web.domain.Noticia;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.sigeiin.main.web.domain.Promocion;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Danie
 */
@Controller
@Slf4j //Anotación para poder incluir mensajes en la ejecución del sistema.
public class promocionController {
    @Autowired
    @Qualifier("PromocionDaoImplementationJPA")
    PromocionDaoImplementation servicePromotion;
    
    @Autowired
    @Qualifier("OfertaEducativaDaoImplementationJPA")
    private iOfertaEducativaDao serviceOfertaEducativa;
    
    @GetMapping({"/admin/promociones","/admin/gestion_promociones"})
    public String gestionPromociones(Model model){
        //log.info("El usuario ha entrado al controlador promociones");
        model.addAttribute("promocion", new Promocion());
        //model.addAttribute("listaPromociones", servicePromotion.listarPromociones());
        model.addAttribute("listaOferta", serviceOfertaEducativa.listarOfertas());
        model.addAttribute("listaPromociones", servicePromotion.listarPromociones());
        
        return "admcostos";
    }
    
    @PostMapping({"/admin/promocion/registrar"})
    public String crearNoticia(Model modelo, Promocion promocion, @RequestParam("fileImagenPromocion") MultipartFile adjunto){
        //Código para el agregado de un archivo de tipo imagen
        if(!adjunto.isEmpty()){
            String uniqueFileName = "promocion/"+UUID.randomUUID().toString() + "_" + adjunto.getOriginalFilename();
            Path rootPath = Paths.get("uploads").resolve(uniqueFileName);
            Path rootAbsolutPath = rootPath.toAbsolutePath();
            log.info("root: " +rootPath);
            log.info("absolute: "+rootAbsolutPath);
            try {
                Files.copy(adjunto.getInputStream(), rootAbsolutPath);
                
                promocion.setAdjuntoPromocion(uniqueFileName);
                
                log.info("El archivo a subirse es: "+promocion.getAdjuntoPromocion());
            } catch (IOException ex) {
                log.info("error: "+ex);
                Logger.getLogger(modalidadController.class.getName()).log(Level.SEVERE, null, ex);
            }
    	log.info("dale");
        }
        
        modelo.addAttribute("promocion", new Promocion());
        //model.addAttribute("listaPromociones", servicePromotion.listarPromociones());
        modelo.addAttribute("listaOferta", serviceOfertaEducativa.listarOfertas());
        modelo.addAttribute("listaPromociones", servicePromotion.listarPromociones());
        servicePromotion.registrarPromocion(promocion);
        return "redirect:/admin/promocion/";
    }
}
