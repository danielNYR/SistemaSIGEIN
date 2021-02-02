package com.sigeiin.main.web.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sigeiin.main.web.dao.iDirectorioDao;
import com.sigeiin.main.web.domain.Directorio;

@Repository("DirectorioRepositoryJPA")
public class DirectorioRepository implements iDirectorioDao{

	
	@PersistenceContext
    private EntityManager em; //Realiza consultas mediante JPA.
	
	@Override
	@Transactional(readOnly = true)
	public List<Directorio> listarDirectorio() {
		return em.createQuery("from Directorio").getResultList();
	}

	@Override
	@Transactional(readOnly = true)
	public Directorio obtenerDirectorio(Long id) {
		return em.find(Directorio.class, id);
	}

	@Override
	@Transactional
	public void deleteDirectorio(Long id) {
		em.remove(obtenerDirectorio(id));
	}

	@Override
	@Transactional
	public void registrarDirectorio(Directorio directorio) {
		if(directorio.getIdDirectorio() == null) {
			em.persist(directorio);
		}else {
			em.merge(directorio);
		}
		
	}
	
	
}
