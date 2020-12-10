/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigeiin.main.web.dao;
import com.sigeiin.main.web.domain.Noticia;
import java.util.List;

/**
 *
 * @author Danie
 */
public interface iNoticiaDao {
    public List<Noticia> listarNoticias();
}
