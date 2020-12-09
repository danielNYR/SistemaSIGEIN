package com.sigeiin.main.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
//Si coloco la etiqueta @RequestMapping("") con valores dentro; 
//podremos poner una ruta al controlador, en mi caso podré dividir 
//el lado de usuario y el lado de administrador con la simple etiqueta
public class indexController {

	//Contenido de métodos handler; cada método handler representa la página web
	//O bien representa una solicitud del usuario para realizar alguna acción, el
	//Controlador la recibe y la procesa de acuerdo a la que se busque llevar a cabo.
	
	//Método 1. - Deploy Index
	@GetMapping({"/admin/","/admin" ,"/admin/index", "/admin/inicio"}) //Cuando en el navegador se solicite el index, desplegará esta vista.
	public String index() {
		
		return "indexadm"; //Esta es la página que devuelve el controlador. También se le llama plantilla
	}
}
