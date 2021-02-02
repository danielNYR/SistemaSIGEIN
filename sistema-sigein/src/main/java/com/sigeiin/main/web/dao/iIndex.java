package com.sigeiin.main.web.dao;

import java.util.List;

import com.sigeiin.main.web.domain.Index;

public interface iIndex {

	public void registrarImagen(Index index);
	public void eliminarImagen(Long id);
	public Index encontrarImagen(Long id);
	public List<Index> listarImagenes();
	
}
