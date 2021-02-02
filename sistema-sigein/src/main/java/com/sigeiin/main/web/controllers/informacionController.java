/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigeiin.main.web.controllers;

import lombok.extern.slf4j.Slf4j;
import com.sigeiin.main.web.domain.InformacionInstitucional;
import com.sigeiin.main.web.domain.ModalidadEducativa;
import com.sigeiin.main.web.repository.InformacionInstitucionalRepository;
import com.sigeiin.main.web.repository.InstitucionRepository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@Slf4j // Anotación para poder incluir mensajes en la ejecución del sistema.
public class informacionController {
	//Idear una forma de incluir en el programa el método delete

	@Autowired
	@Qualifier("InformacionInstitucionalRepositoryJPA")
	private InformacionInstitucionalRepository serviceInfoIns;
	
	@Autowired
	@Qualifier("InstitucionRepositoryJPA")
	private InstitucionRepository serviceInstitucion;

	String adjunto_vision_institucional = "";
	String adjunto_mision_institucional = "";

	@GetMapping({ "/admin/conocenos", "/admin/gconocenos" })
	public String listar(Model model) {
		if(serviceInfoIns.obtenerInformacion(1L)==null) {
			model.addAttribute("informacion", new InformacionInstitucional());
		}else {
			model.addAttribute("informacion", serviceInfoIns.obtenerInformacion(1L));
		}
		return "admconocenos";
	}


	// Este método se recicla ya que será necesario para mision y visión
	@PostMapping({ "/admin/conocenos/registro" })
	public String registrarVision(Model model, InformacionInstitucional institucion,
			@RequestParam("fileAdjuntoMision") MultipartFile adjuntoMi, @RequestParam("fileAdjuntoVision") MultipartFile adjuntoVi) {
		institucion.setInstitucionInformacion(serviceInstitucion.obtenerInstitucion(1L));
		String uniqueFileName = "";
		if (!adjuntoMi.isEmpty() && adjuntoMi != null) {
			String file = writeFile(adjuntoMi);
			institucion.setAdjuntoMision(file);
			log.info("El archivo a subir, es: " + file);

		} else {
			alterValuesAdjuntos(institucion);
		}
		if(!adjuntoVi.isEmpty() && adjuntoVi!=null) {
			String secondFile = writeFile(adjuntoVi);
			institucion.setAdjuntoVision(secondFile);
		}else {
			alterValuesAdjuntos(institucion);
		}
		serviceInfoIns.registrarMision(institucion);
		model.addAttribute("conocenos", new InformacionInstitucional());
		return "redirect:/admin/conocenos";
	}


	@RequestMapping(value = "/admin/conocenos/editar/")
	public String editarVision(Model model) {

		InformacionInstitucional info = serviceInfoIns.obtenerInformacion(1L);
		adjunto_vision_institucional = info.getAdjuntoMision();
		adjunto_mision_institucional = info.getAdjuntoVision();
		model.addAttribute("informacion", serviceInfoIns.obtenerInformacion(1L));

		return "admconocenos";
	}

	// Método que detecta si algún valor en los archivos es null
	public void alterValuesAdjuntos(InformacionInstitucional institucion) {
		if (institucion.getAdjuntoMision() == null) {
			institucion.setAdjuntoMision(adjunto_mision_institucional);
		}
		if (institucion.getAdjuntoVision() == null) {
			institucion.setAdjuntoVision(adjunto_vision_institucional);
		}
	}

	// Método que se encarga de la gestión del archivo a la hora de escribirlo.
	public String writeFile(MultipartFile adjunto) {
		String uniqueFileName = "";

		// Siempre habrá un delete file ya que es el único.
		// deleteFile();
		uniqueFileName = "institucion/" + UUID.randomUUID().toString() + "_" + adjunto.getOriginalFilename();
		Path rootPath = Paths.get("uploads").resolve(uniqueFileName);
		Path rootAbsolutPath = rootPath.toAbsolutePath();
		log.info("root: " + rootPath);
		log.info("absolute: " + rootAbsolutPath);
		try {
			Files.copy(adjunto.getInputStream(), rootAbsolutPath);
		} catch (IOException ex) {
			log.info("error: " + ex);
			Logger.getLogger(modalidadController.class.getName()).log(Level.SEVERE, null, ex);
		}
		return uniqueFileName;
	}

	// Método Delete File
	private boolean deleteFile(MultipartFile adjunto) {

		Path rootPath = Paths.get("uploads").resolve(adjunto.getOriginalFilename()).toAbsolutePath();
		File archivo = rootPath.toFile();
		if (archivo.exists() && archivo.canRead()) {
			return archivo.delete();
		}
		return false;
	}
}