/*
 * 
 */
package com.sigeiin.main.web.dao;
import com.sigeiin.main.web.domain.Usuario;
import java.util.List;

/**
 *
 * @author Danie
 */
public interface iUsuario {
    
    public List<Usuario> listarUsuarios();
    public void agregarUsuario(Usuario usuario);
    public Usuario encontrarUsuario(Long id);
    public void eliminarUsuario(Long id);
    
}
