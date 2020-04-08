package br.com.microservice.service;

import java.util.List;

import br.com.microservice.model.dto.PmaParametersDto;
import br.com.microservice.model.dto.UpdatePmaParameters;
import br.com.microservice.model.enun.ActionEnum;

public interface PmaParametersService {

	PmaParametersDto created(PmaParametersDto pmaDto);

	void delete(Integer id);

	List<PmaParametersDto> listAll();

	List<PmaParametersDto> listByPartners(String partner);

	List<PmaParametersDto> listByReasonCode(Integer reasonCode);

	List<PmaParametersDto> listByAction(ActionEnum action);
	
	List<PmaParametersDto> listByLivpnr(String livpnr);
	
	PmaParametersDto update(Integer id, UpdatePmaParameters updateParameters);
}
