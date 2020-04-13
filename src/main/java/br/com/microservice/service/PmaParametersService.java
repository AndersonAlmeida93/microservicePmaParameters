package br.com.microservice.service;

import java.util.List;

import br.com.microservice.model.dto.PmaParametersDto;
import br.com.microservice.model.dto.UpdatePmaParameters;
import br.com.microservice.model.enun.ActionEnum;

public interface PmaParametersService {

	PmaParametersDto created(PmaParametersDto pmaDto);

	void delete(Integer id);
	
	PmaParametersDto update(Integer id, UpdatePmaParameters updateParameters);
	
	List<PmaParametersDto> getPmaDtos(String partner, Integer reasonCode, ActionEnum actionPma ,String livpnr);
}
