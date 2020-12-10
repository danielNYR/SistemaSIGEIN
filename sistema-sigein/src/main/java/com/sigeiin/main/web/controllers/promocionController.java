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
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.sigeiin.main.web.domain.Promocion;

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
        //model.addAttribute("promocion", new Promocion());
        //model.addAttribute("listaPromociones", servicePromotion.listarPromociones());
        //model.addAttribute("listaOferta", serviceOfertaEducativa.listarOfertas());
        model.addAttribute("listaPromociones", servicePromotion.listarPromociones());
        
        return "admcostos";
    }
}
