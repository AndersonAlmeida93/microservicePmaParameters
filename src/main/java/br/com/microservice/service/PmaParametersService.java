package br.com.microservice.service;

import java.util.List;

import br.com.microservice.model.dto.PmaParametersDto;
import br.com.microservice.model.dto.PmaParametersRequest;
import br.com.microservice.model.dto.UpdatePmaParameters;

public interface PmaParametersService {

	PmaParametersDto created(PmaParametersDto pmaDto);

	boolean delete(Integer id);

	PmaParametersDto update(Integer id, UpdatePmaParameters updateParameters);

	List<PmaParametersDto> getPmaDtos(PmaParametersRequest request);
}
