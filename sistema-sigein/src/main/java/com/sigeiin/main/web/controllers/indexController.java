package com.sigeiin.main.web.controllers;

import com.sigeiin.main.web.dao.InstitucionRepository;
import com.sigeiin.main.web.domain.Institucion;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
//Si coloco la etiqueta @RequestMapping("") con valores dentro; 
//podremos poner una ruta al controlador, en mi caso podré dividir 
//el lado de usuario y el lado de administrador con la simple etiqueta
@Slf4j //Anotación para poder incluir mensajes en la ejecución del sistema.
public class indexController {

    //Contenido de métodos handler; cada método handler representa la página web
    //O bien representa una solicitud del usuario para realizar alguna acción, el
    //Controlador la recibe y la procesa de acuerdo a la que se busque llevar a cabo.
    //Método 1. - Deploy Index
    
    @Autowired
    @Qualifier("InstitucionRepositoryJPA")
    private InstitucionRepository serviceInstitucion;
    
    @GetMapping({"/admin/", "/admin", "/admin/index", "/admin/inicio"}) //Cuando en el navegador se solicite el index, desplegará esta vista.
    public String index(Model model) {
        
        model.addAttribute("institucion", new Institucion());
        //Se obtiene solo el primer registro ya que es el único que existe.
        model.addAttribute("objetoInstitucion", serviceInstitucion.obtenerInstitucion(1L));
        return "indexadm"; //Esta es la página que devuelve el controlador. También se le llama plantilla
        
    }
    
    @PostMapping({"/admin/registrar/nombreInstitucional"})
    public String registrarNombreInstitucional(Model model, @RequestParam("nombreInstitucional") String nombre){
        Institucion institucion = null;
        if(serviceInstitucion.listarInstitucion().size() > 0){
            if(!nombre.isEmpty() && nombre != null){
                institucion = serviceInstitucion.obtenerInstitucion(1L);
                institucion.setNombreInstitucion(nombre);
                serviceInstitucion.registerInstitucion(institucion);
            }
        }else{
            createFirstRegister(institucion);
            log.info("Se ha creado el registro de la institucion");
        }
        //significa que hay registros
        return "redirect:/admin";
    }
    
    private boolean createFirstRegister(Institucion institucion){
        
        
        if(serviceInstitucion.obtenerInstitucion(1L) != null){
            //institucion.setIdInstitucion(id);
            institucion.setAcronimoInstitucion("SA");
            institucion.setFacebookInstitucional("SA");
            institucion.setLocalizacionInstitucional("SA");
            institucion.setLogoInstitucion("SA");
            institucion.setNombreInstitucion("SA");
            institucion.setRazonInstitucional("SA");
            institucion.setYoutubeInstitucional("SA");
            serviceInstitucion.registerInstitucion(institucion);
            return true;
        }else{
            return false;
        }
    }
}
