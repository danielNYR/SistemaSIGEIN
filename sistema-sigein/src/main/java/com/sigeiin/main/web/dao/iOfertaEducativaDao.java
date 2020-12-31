/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigeiin.main.web.dao;
import com.sigeiin.main.web.domain.OfertaEducativa;

import java.util.List;

/**
 *
 * @author Danie
 */
public interface iOfertaEducativaDao {
    
    public List<OfertaEducativa> listarOfertas();
    public void registrarOferta(OfertaEducativa oferta);
    public OfertaEducativa encontrarOferta(Long id);
    public void eliminarOferta(Long id);
}
