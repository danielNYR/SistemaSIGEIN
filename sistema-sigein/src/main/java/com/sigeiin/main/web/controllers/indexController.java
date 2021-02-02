package com.sigeiin.main.web.controllers;

import lombok.extern.slf4j.Slf4j;

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

import com.sigeiin.main.web.domain.Index;
import com.sigeiin.main.web.domain.Institucion;
import com.sigeiin.main.web.repository.AspiranteDaoImplementation;
import com.sigeiin.main.web.repository.IndexRepository;
import com.sigeiin.main.web.repository.InstitucionRepository;
import com.sigeiin.main.web.repository.NoticiaDaoImplementation;

@Controller
//Si coloco la etiqueta @RequestMapping("") con valores dentro; 
//podremos poner una ruta al controlador, en mi caso podré dividir 
//el lado de usuario y el lado de administrador con la simple etiqueta
@Slf4j // Anotación para poder incluir mensajes en la ejecución del sistema.
public class indexController {

    // Contenido de métodos handler; cada método handler representa la página web
    // O bien representa una solicitud del usuario para realizar alguna acción, el
    // Controlador la recibe y la procesa de acuerdo a la que se busque llevar a
    // cabo.
    // Método 1. - Deploy Index
//Cambiar todo el formulario para evitar el error de la respuesta
    @Autowired
    @Qualifier("InstitucionRepositoryJPA")
    private InstitucionRepository serviceInstitucion;

    @Autowired
    @Qualifier("IndexRepositoryJPA")
    private IndexRepository serviceIndex;

    @Autowired
    @Qualifier("AspiranteDaoImplementationJPA")
    private AspiranteDaoImplementation serviceAspirante;

    @Autowired
    @Qualifier("NoticiaRepositoryJPA")
    private NoticiaDaoImplementation serviceNoticia;

    //Variables para los archivos
    private String archivo_actual_logo = "";
    private String archivo_temporal_img_idx = "";

    @GetMapping({"/admin/", "/admin", "/admin/index", "/admin/inicio"}) // Cuando en el navegador se solicite el																	// index, desplegará esta vista.
    public String index(Model model) {
        Institucion institucion = serviceInstitucion.obtenerInstitucion(1L);
        if (institucion != null) {
            archivo_actual_logo = institucion.getLogoInstitucion();
            model.addAttribute("institucion", institucion);
        } else {
            model.addAttribute("institucion", new Institucion());
        }

        model.addAttribute("aspirantesRegs", serviceAspirante.contarAlumnosInscritor());
        model.addAttribute("noticiasPublic", serviceNoticia.noticiasPublicadas());
        model.addAttribute("index", new Index());
        // Se obtiene solo el primer registro ya que es el único que existe.
        //model.addAttribute("objetoInstitucion", serviceInstitucion.obtenerInstitucion(1L));
        model.addAttribute("listaIndex", serviceIndex.listarImagenes());

        return "indexadm"; // Esta es la página que devuelve el controlador. También se le llama plantilla
    }

    @PostMapping({"/admin/registrar/institucion"})
    public String registrarNombreInstitucional(Model model, Institucion institucion, @RequestParam("fileInstitucion") MultipartFile adjunto) {
        if (!adjunto.isEmpty()) {
            if (institucion.getLogoInstitucion() != null && institucion.getIdInstitucion() > 0) {
                //Delete file
            }
            String uniqueFileName = UUID.randomUUID().toString() + "_" + adjunto.getOriginalFilename();
            Path rootPath = Paths.get("uploads").resolve(uniqueFileName);
            Path rootAbsolutPath = rootPath.toAbsolutePath();
            try {

                Files.copy(adjunto.getInputStream(), rootAbsolutPath);
                institucion.setLogoInstitucion(uniqueFileName);
                //log.info("El archivo a subirse es: "+modalidad.getAdjuntoModalidadEducativa());
            } catch (IOException ex) {
                //Meter excepción org.apache.tomcat.util.http.fileupload.impl.SizeLimitExceededException:
                Logger.getLogger(modalidadController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            //El archivo entrante viene en null
            if (institucion.getLogoInstitucion() == null && archivo_actual_logo.length() > 0) { //Se comprueba que el archivo que viene sí sea null
                //De ser el caso se le inserta el obtenido en el módulo editar, identificado por la variable 
                institucion.setLogoInstitucion(archivo_actual_logo); //Archivo_adjunto_modalidad
            }
        }
        //Registrando
        serviceInstitucion.registerInstitucion(institucion);
        return "redirect:/admin";
    }

    @PostMapping({"/admin/registrar/imagenIndex"})
    public String registrarImagenIndex(Model model, Index index, @RequestParam("fileIndex") MultipartFile adjunto) {
        if (!adjunto.isEmpty()) {
            if (index.getAdjuntoIndex() != null) {
                // Delete file
            }
            // Rutas del archivo del archivo que será subido
            String uniqueFileName = UUID.randomUUID().toString() + "_" + adjunto.getOriginalFilename();
            Path rootPath = Paths.get("uploads").resolve(uniqueFileName);
            Path rootAbsolutPath = rootPath.toAbsolutePath();

            try {
                Files.copy(adjunto.getInputStream(), rootAbsolutPath);
                index.setAdjuntoIndex(uniqueFileName);
            } catch (IOException ex) {
                Logger.getLogger(indexController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (index.getAdjuntoIndex() == null && adjunto.isEmpty()) {
            index.setAdjuntoIndex(archivo_temporal_img_idx);
        }
        serviceIndex.registrarImagen(index);
        return "redirect:/admin";
    }

    //Método que edita las imágenes del index
    @RequestMapping(value = "/admin/imagenIndex/{id}")
    public String editarImagenIndex(@PathVariable(value = "id") Long id, Model model) {
        Index index = null;
        if (id > 0) {
            index = serviceIndex.encontrarImagen(id);
            archivo_temporal_img_idx = index.getAdjuntoIndex();
        }
        model.addAttribute("institucion", new Institucion());
        model.addAttribute("index", index);
        // Se obtiene solo el primer registro ya que es el único que existe.
        model.addAttribute("objetoInstitucion", serviceInstitucion.obtenerInstitucion(1L));
        return "indexadm";
    }

    private boolean createFirstRegister(Institucion institucion) {

        if (serviceInstitucion.obtenerInstitucion(1L) == null) {
            institucion.setIdInstitucion(1L);
            institucion.setAcronimoInstitucion("SA");
            institucion.setFacebookInstitucional("SA");
            institucion.setLatitudInstitucional("SA");
            institucion.setLongitudInstitucional("SA");
            institucion.setLogoInstitucion("SA");
            institucion.setNombreInstitucion("SA");
            institucion.setRazonInstitucional("SA");
            institucion.setYoutubeInstitucional("SA");
            serviceInstitucion.registerInstitucion(institucion);
            return true;
        } else {
            return false;
        }
    }

    private boolean comprobarTamanoRegistros() {

        int registros = 0; //Por ahora será 0
        if (registros <= 9) {
            return true;
        } else if (registros >= 10 || registros < -0) {
            return false;
        }
        return false;
    }

    private boolean comprobarExistenciaInstitucion() {
        int registro = serviceInstitucion.listarInstitucion().size();
        if (registro > 0 && registro < 2) {
            return true;
        } else {
            return false;
        }
    }
}
