/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigeiin.main.web.dao;
import com.sigeiin.main.web.domain.AreaInstitucional;
import java.util.List;

/**
 *
 * @author Danie
 */
public interface iAreaInstitucional {
    List <AreaInstitucional> listarAreas();
    public void registrarArea(AreaInstitucional area);
    public AreaInstitucional encontrarArea(Long id);
    public void eliminarArea(Long id);
}
