package br.com.microservice.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.microservice.exception.BadRequestException;
import br.com.microservice.exception.NotFoudException;
import br.com.microservice.model.PmaParameters;
import br.com.microservice.model.dto.PmaParametersDto;
import br.com.microservice.model.dto.PmaParametersRequest;
import br.com.microservice.model.dto.UpdatePmaParameters;
import br.com.microservice.repository.PmaParametersRepository;
import br.com.microservice.specification.PmaSpecification;

@Service
public class PmaParametersServiceImpl implements PmaParametersService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private PmaParametersRepository pmaRepository;

	@Override
	@Transactional
	public PmaParametersDto created(PmaParametersDto pmaDto) {

		PmaParameters pmaParam = modelMapper.map(pmaDto, PmaParameters.class);
		if (!pmaParam.getActionPma().name().equals("UPDATE")) {
			pmaParam.setValue(null);
		}
		pmaRepository.save(pmaParam);

		return modelMapper.map(pmaParam, PmaParametersDto.class);
	}

	@Override
	public List<PmaParametersDto> getPmaDtos(PmaParametersRequest request) {

		List<PmaParameters> pmas = pmaRepository.findAll(PmaSpecification.findByParam(request));
		if (pmas.isEmpty()) {
			throw new NotFoudException("No Registry Found!");
		}
		return pmas.stream().map(obj -> modelMapper.map(obj, PmaParametersDto.class)).collect(Collectors.toList());

	}

	@Override
	@Transactional
	public PmaParametersDto update(Integer id, UpdatePmaParameters updateParameters) {

		if (id == null || id <= 0) {

			throw new BadRequestException("Invalid Id " + id);
		}
		Optional<PmaParameters> option = pmaRepository.findById(id);
		if (!option.isPresent()) {

			throw new NotFoudException("No Registry Found!");
		}

		PmaParameters action = pmaRepository.findActionPmaById(id);
		String actionPma = action.getActionPma().name();
		PmaParameters pmaParameters = pmaRepository.getOne(id);
		pmaParameters.setDescriptionCode(updateParameters.getDescriptionCode());

		if (StringUtils.equals(actionPma, "UPDATE")) {
			pmaParameters.setValue(updateParameters.getValue());
		}
		pmaRepository.save(pmaParameters);
		return modelMapper.map(pmaParameters, PmaParametersDto.class);

	}

	@Override
	@Transactional
	public boolean delete(Integer id) {

		if (id == null || id <= 0) {

			throw new BadRequestException("Invalid Id " + id);
		}
		Optional<PmaParameters> pma = pmaRepository.findById(id);

		if (!pma.isPresent()) {
			throw new NotFoudException("No Registry Found!");
		}

		pmaRepository.deleteById(id);
		return true;

	}

}
