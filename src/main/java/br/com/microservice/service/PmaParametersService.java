package br.com.microservice.service;

import java.util.List;

import br.com.microservice.model.dto.PmaParametersDto;
import br.com.microservice.model.dto.PmaParametersRequest;
import br.com.microservice.model.dto.UpdatePmaParameters;

public interface PmaParametersService {

	PmaParametersDto created(PmaParametersDto pmaDto);

	List<PmaParametersDto> getPmaDtos(PmaParametersRequest request);

	PmaParametersDto update(Integer id, UpdatePmaParameters updateParameters);

	boolean delete(Integer id);

}
