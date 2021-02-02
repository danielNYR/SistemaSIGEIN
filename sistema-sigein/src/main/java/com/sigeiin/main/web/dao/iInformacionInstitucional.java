package com.sigeiin.main.web.dao;

import com.sigeiin.main.web.domain.InformacionInstitucional;

public interface iInformacionInstitucional {

	public InformacionInstitucional obtenerInformacion(Long id);
	public void modificarInformacionInstitucional(InformacionInstitucional info);
	public void modificarVision(String mision, String adjunto);
	public void modificarMision(String vision, String adjunto);
}
