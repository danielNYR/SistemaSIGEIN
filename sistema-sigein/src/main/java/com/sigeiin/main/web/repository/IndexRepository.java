package com.sigeiin.main.web.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.stereotype.Repository;

import com.sigeiin.main.web.dao.iIndex;
import com.sigeiin.main.web.domain.Index;


@Repository("IndexRepositoryJPA")
public class IndexRepository implements iIndex{

	@PersistenceContext
	EntityManager em;
	
	
	@Override
	@Transactional
	public void registrarImagen(Index index) {
		if(index.getIdIndex() != null) {
			em.merge(index);
		}else {
			em.persist(index);
		}
	}

	@Transactional
	@Override
	public void eliminarImagen(Long id) {
		em.remove(encontrarImagen(id));
	}

	@Transactional(readOnly = true)
	@Override
	public Index encontrarImagen(Long id) {
		return em.find(Index.class, id);
	}

	@Transactional(readOnly = true)
	@Override
	public List<Index> listarImagenes() {
		return em.createQuery("from Inicio").getResultList();
	}

	
}
