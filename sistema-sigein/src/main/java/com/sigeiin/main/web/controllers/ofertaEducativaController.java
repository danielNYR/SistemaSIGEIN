/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigeiin.main.web.controllers;

import com.sigeiin.main.web.dao.ModalidadEducativaDaoImplementation;
import com.sigeiin.main.web.dao.iOfertaEducativaDao;
import com.sigeiin.main.web.domain.OfertaEducativa;
import java.io.File;
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
public class ofertaEducativaController {

    @Autowired
    @Qualifier("OfertaEducativaDaoImplementationJPA")
    private iOfertaEducativaDao service_OfertaEducativa;

    @Autowired
    @Qualifier("ModalidadEducativaDaoImplementationJPA")
    private ModalidadEducativaDaoImplementation serviceModalidad;

    @GetMapping({"/admin/ofeducativa", "/admin/oferta", "/admin/oferta_educativa"})
    public String gestionNoticia(Model model) {
        model.addAttribute("oferta", new OfertaEducativa());
        model.addAttribute("listaModalidad", serviceModalidad.listarOfertas());
        model.addAttribute("listaOferta", service_OfertaEducativa.listarOfertas()); //Con esto hacemos un llamado a todos los datos en la base de datos que se obtuvieron
        //log.info("El usuario ha entrado al controlador aspirante");
        return "ofeducativa";
    }

    @PostMapping({"/admin/ofeducativa/registrar"})
    public String crearNoticia(Model modelo, OfertaEducativa oferta, @RequestParam("filePlanEstudios") MultipartFile adjuntoPE,
            @RequestParam("fileReticulaEstudios") MultipartFile adjuntoReticula, @RequestParam("fileOfertaEducativa1") MultipartFile adjuntoOferta1,
            @RequestParam("fileOfertaEducativa2") MultipartFile adjuntoOferta2, @RequestParam("fileOfertaEducativa3") MultipartFile adjuntoOferta3) {
        //Código para el agregado de un archivo de tipo imagen

        //Variables para cada uno de los archivos. Definidad
        String uniqueFileNameAdjuntoPE = ""; //Adjunto plan estudios
        String uniqueFileNameReticula = ""; //Adjunto reticula estudios
        String uniqueFileNameOferta1 = ""; //Ajunto oferta educativa, img 1
        String uniqueFileNameOferta2 = ""; //Adjunto oferta educativa, img2
        String uniqueFileNameOferta3 = ""; //Adjunto oferta educativa, img3

        //Rutas absolutas
        Path rootAbsolutPath = null;
        Path rootAbsolutePathRE = null;
        Path rootAbsolutePathR1 = null;
        Path rootAbsolutePathR2 = null;
        Path rootAbsolutePathR3 = null;
        

        if (!adjuntoPE.isEmpty()) {
            uniqueFileNameAdjuntoPE = "educativa/" + UUID.randomUUID().toString() + "_" + adjuntoPE.getOriginalFilename();
            Path rootPath = Paths.get("uploads").resolve(uniqueFileNameAdjuntoPE);
            rootAbsolutPath = rootPath.toAbsolutePath();
        } else {//Apartado en que se elimina el archivo
            if(oferta.getIdOfertaEducativa()>0){
                Path pathObtenido = Paths.get("uploads").resolve(oferta.getPlanEstudiosOfertaEducativa()).toAbsolutePath();
                deleteFile(pathObtenido);
            }
        }
        if (!adjuntoReticula.isEmpty()) {
            uniqueFileNameReticula = "educativa/" + UUID.randomUUID().toString() + "_" + adjuntoReticula.getOriginalFilename();
            Path rootPath2 = Paths.get("uploads").resolve(uniqueFileNameReticula);
            rootAbsolutePathRE = rootPath2.toAbsolutePath();
        } else {//Apartado en que se elimina el archivo
            if(oferta.getIdOfertaEducativa()>0){
            Path pathObtenido = Paths.get("uploads").resolve(oferta.getReticulaOfertaEducativa()).toAbsolutePath();
            deleteFile(pathObtenido);
            }
        }
        if (!adjuntoOferta1.isEmpty()) {
            uniqueFileNameOferta1 = "educativa/" + UUID.randomUUID().toString() + "_" + adjuntoOferta1.getOriginalFilename();
            Path rootPath3 = Paths.get("uploads").resolve(uniqueFileNameOferta1);
            rootAbsolutePathR1 = rootPath3.toAbsolutePath();
        } else {//Apartado en que se elimina el archivo
            if(oferta.getIdOfertaEducativa()>0){
            Path pathObtenido = Paths.get("uploads").resolve(oferta.getAdjuntoOfertaEducativa()).toAbsolutePath();
            deleteFile(pathObtenido);
            }
        }
        if (!adjuntoOferta2.isEmpty()) {
            
            uniqueFileNameOferta2 = "educativa/" + UUID.randomUUID().toString() + "_" + adjuntoOferta2.getOriginalFilename();
            Path rootPath4 = Paths.get("uploads").resolve(uniqueFileNameOferta2);
            rootAbsolutePathR2 = rootPath4.toAbsolutePath();
        } else {//Apartado en que se elimina el archivo
            if(oferta.getIdOfertaEducativa()>0){
            Path pathObtenido = Paths.get("uploads").resolve(oferta.getAdjuntoAcercaOfertaEducativa()).toAbsolutePath();
            deleteFile(pathObtenido);
            }
        }
        if (!adjuntoOferta3.isEmpty()) {
            uniqueFileNameOferta3 = "educativa/" + UUID.randomUUID().toString() + "_" + adjuntoOferta3.getOriginalFilename();
            Path rootPath5 = Paths.get("uploads").resolve(uniqueFileNameOferta3);
            rootAbsolutePathR3 = rootPath5.toAbsolutePath();
        } else {
            //Apartado en que se elimina el archivo
            if(oferta.getIdOfertaEducativa()>0){
            Path pathObtenido = Paths.get("uploads").resolve(oferta.getAdjuntoObjetivoOfertaEducativa()).toAbsolutePath();
            deleteFile(pathObtenido);
            }
        }
        try {

            if (!adjuntoPE.isEmpty()) {
                Files.copy(adjuntoPE.getInputStream(), rootAbsolutPath);
                oferta.setPlanEstudiosOfertaEducativa(uniqueFileNameAdjuntoPE);
            }
            if (!adjuntoReticula.isEmpty()) {
                Files.copy(adjuntoReticula.getInputStream(), rootAbsolutePathRE);
                oferta.setReticulaOfertaEducativa(uniqueFileNameReticula);
            }
            if (!adjuntoOferta1.isEmpty()) {
                Files.copy(adjuntoOferta1.getInputStream(), rootAbsolutePathR1);
                oferta.setAdjuntoOfertaEducativa(uniqueFileNameOferta1);
            }
            if (!adjuntoOferta2.isEmpty()) {
                Files.copy(adjuntoOferta2.getInputStream(), rootAbsolutePathR2);
                oferta.setAdjuntoAcercaOfertaEducativa(uniqueFileNameOferta2);
            }
            if (!adjuntoOferta3.isEmpty()) {
                Files.copy(adjuntoOferta3.getInputStream(), rootAbsolutePathR3);
                oferta.setAdjuntoObjetivoOfertaEducativa(uniqueFileNameOferta3);
            }

        } catch (IOException ex) {
            log.info("error: " + ex);
            Logger.getLogger(modalidadController.class.getName()).log(Level.SEVERE, null, ex);
        }
        log.info("dale");
        modelo.addAttribute("oferta", new OfertaEducativa());
        //model.addAttribute("listaPromociones", servicePromotion.listarPromociones());
        modelo.addAttribute("listaModalidad", serviceModalidad.listarOfertas());
        modelo.addAttribute("listaOferta", service_OfertaEducativa.listarOfertas());
        service_OfertaEducativa.registrarOferta(oferta);
        return "redirect:/admin/promocion/";
    }

    @GetMapping({"/admin/ofeducativa/{id}"})
    public String gestionNoticiaIndividual(@PathVariable(value = "id") Long id, Model model) {
        OfertaEducativa oferta = service_OfertaEducativa.encontrarOferta(id);

        model.addAttribute("oferta", oferta);
        model.addAttribute("listaModalidad", serviceModalidad.listarOfertas());
        model.addAttribute("listaOferta", service_OfertaEducativa.listarOfertas()); //Con esto hacemos un llamado a todos los datos en la base de datos que se obtuvieron
        //log.info("El usuario ha entrado al controlador aspirante");
        return "ofertas";
    }

    //Método modificar
    @RequestMapping(value = "/admin/oferta_educativa/editar/{id}")
    public String editar(@PathVariable(value = "id") Long id, Model model) {
        OfertaEducativa oferta = null;
        if (id > 0) {
            oferta = service_OfertaEducativa.encontrarOferta(id);
        } else {
            return "redirect:/error/";
        }
        model.addAttribute("oferta", oferta);
        return "ofeducativa";
    }

    //Método Eliminar
    @RequestMapping(value = "/admin/oferta_educativa/eliminar/{id}")
    public String eliminar(@PathVariable(value = "id") Long id) {
        if (id > 0) {
            service_OfertaEducativa.eliminarOferta(id);
            //Método que elimina todos los archivos
            deleteAllFilesOferta(id);
        }
        return "ofeducativa";
    }

    //Método para eliminar archivo en el servidor
    private void deleteFile(Path rootPath) {
        File archivo = rootPath.toFile();
        if (archivo.exists() && archivo.canRead()) {
            archivo.delete();
        }
    }
    
    private void deleteAllFilesOferta(Long id){
        OfertaEducativa oferta = service_OfertaEducativa.encontrarOferta(id);
        Path rootPath = Paths.get("uploads").resolve(oferta.getAdjuntoAcercaOfertaEducativa()).toAbsolutePath();
        Path file2 = Paths.get("uploads").resolve(oferta.getAdjuntoObjetivoOfertaEducativa()).toAbsolutePath();
        Path file3 = Paths.get("uploads").resolve(oferta.getAdjuntoOfertaEducativa()).toAbsolutePath();
        Path file4 = Paths.get("uploads").resolve(oferta.getPlanEstudiosOfertaEducativa()).toAbsolutePath();
        Path file5 = Paths.get("uploads").resolve(oferta.getReticulaOfertaEducativa()).toAbsolutePath();
        File archivo = rootPath.toFile();
        File archivo1 = file2.toFile();
        File archivo2 = file3.toFile();
        File archivo3 = file4.toFile();
        File archivo4 = file5.toFile();
        if(archivo.exists() && archivo.canRead()){
            archivo.delete();
        }
        if(archivo1.exists() && archivo1.canRead()){
            archivo1.delete();
        }
        if(archivo2.exists() && archivo2.canRead()){
            archivo2.delete();
        }
        if(archivo3.exists() && archivo3.canRead()){
            archivo3.delete();
        }
        if(archivo4.exists() && archivo4.canRead()){
            archivo4.delete();
        }
    }
}
