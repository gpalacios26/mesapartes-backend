package com.gregpalacios.mesapartes.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gregpalacios.mesapartes.model.Solicitud;
import com.gregpalacios.mesapartes.repo.IGenericRepo;
import com.gregpalacios.mesapartes.repo.ISolicitudRepo;
import com.gregpalacios.mesapartes.service.ISolicitudService;

@Service
public class SolicitudServiceImpl extends CRUDImpl<Solicitud, Integer> implements ISolicitudService {

	@Autowired
	private ISolicitudRepo repo;
	
	@Override
	protected IGenericRepo<Solicitud, Integer> getRepo() {
		return repo;
	}

}
