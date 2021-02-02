package com.sigeiin.main.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.sigeiin.main.web.domain.Directorio;
import com.sigeiin.main.web.repository.AreaInstitucionalDaoImplementation;
import com.sigeiin.main.web.repository.DirectorioRepository;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j //Anotación para poder incluir mensajes en la ejecución del sistema.
public class directorioController {
	
	@Autowired
	@Qualifier("DirectorioRepositoryJPA")
	DirectorioRepository serviceDirectorio;
	
	@Autowired
	AreaInstitucionalDaoImplementation serviceArea;
	
	String adjunto_directorio_actual = "";
	
	@GetMapping({"/admin/directorio"})
	public String deployDirectorio(Model model) {
		model.addAttribute("directorio", new Directorio());
		model.addAttribute("listaDirectorio", serviceDirectorio.listarDirectorio());
		model.addAttribute("listaAreas", serviceArea.listarAreas());
		return "admdirectorio";
	}
	
	@PostMapping({"/admin/directorio/registro"})
	public String registrarNuevo(Model model, Directorio directorio) {
		log.info("Directorio: " + directorio.toString());
			serviceDirectorio.registrarDirectorio(directorio);
		
		return "redirect:/admin/directorio";
	}
	
	
}
