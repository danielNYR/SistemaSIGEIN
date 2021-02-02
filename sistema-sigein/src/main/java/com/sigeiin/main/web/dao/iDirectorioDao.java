package com.sigeiin.main.web.dao;
import java.util.List;
import com.sigeiin.main.web.domain.Directorio;

public interface iDirectorioDao {

	public List<Directorio> listarDirectorio();
	public Directorio obtenerDirectorio(Long id);
	public void deleteDirectorio(Long id);
	public void registrarDirectorio(Directorio directorio);
}
