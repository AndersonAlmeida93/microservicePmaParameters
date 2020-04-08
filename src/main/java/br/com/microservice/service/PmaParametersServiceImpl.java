package br.com.microservice.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.microservice.model.PmaParameters;
import br.com.microservice.model.dto.PmaParametersDto;
import br.com.microservice.model.dto.UpdatePmaParameters;
import br.com.microservice.model.enun.ActionEnum;
import br.com.microservice.repository.PmaParametersRepository;

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
		pmaRepository.save(pmaParam);
		return modelMapper.map(pmaParam, PmaParametersDto.class);

	}

	@Override
	public void delete(Integer id) {

		Optional<PmaParameters> pma = pmaRepository.findById(id);
		pmaRepository.deleteById(id);

	}

	private PmaParametersDto converToDto(PmaParameters pma) {

		PmaParametersDto pmaDto = modelMapper.map(pma, PmaParametersDto.class);
		return pmaDto;
	}

	@Override
	public List<PmaParametersDto> listAll() {

		List<PmaParameters> pmas = pmaRepository.findAll();
		return pmas.stream().map(this::converToDto).collect(Collectors.toList());
	}

	@Override
	public List<PmaParametersDto> listByPartners(String partner) {

		List<PmaParameters> pmas = pmaRepository.findByPartnerIgnoreCase(partner);
		return pmas.stream().map(this::converToDto).collect(Collectors.toList());
	}

	@Override
	public List<PmaParametersDto> listByReasonCode(Integer reasonCode) {

		List<PmaParameters> pmas = pmaRepository.findByReasonCode(reasonCode);
		return pmas.stream().map(this::converToDto).collect(Collectors.toList());

	}

	@Override
	public List<PmaParametersDto> listByAction(ActionEnum action) {

		List<PmaParameters> pmas = pmaRepository.findByActionPma(action);
		return pmas.stream().map(this::converToDto).collect(Collectors.toList());
	}

	@Override
	public List<PmaParametersDto> listByLivpnr(String livpnr) {

		List<PmaParameters> pmas = pmaRepository.findByLivpnr(livpnr);
		return pmas.stream().map(this::converToDto).collect(Collectors.toList());
	}

	@Override
	public PmaParametersDto update(Integer id, UpdatePmaParameters updateParameters) {

		Optional<PmaParameters> optiona = pmaRepository.findById(id);
		if (!optiona.isPresent()) {

		}

		PmaParameters action = pmaRepository.findActionPmaById(id);
		String actionPma = action.getActionPma().toString();
		if (StringUtils.equals(actionPma, "UPDATE")) {
			PmaParameters pmaParameters = pmaRepository.getOne(id);
			pmaParameters.setDescriptionCode(updateParameters.getDescriptionCode());
			pmaParameters.setValue(updateParameters.getValue());
			pmaRepository.save(pmaParameters);
			return modelMapper.map(pmaParameters, PmaParametersDto.class);
		} else {
			PmaParameters pma = pmaRepository.getOne(id);
			pma.setDescriptionCode(updateParameters.getDescriptionCode());
			pmaRepository.save(pma);
			return modelMapper.map(pma, PmaParametersDto.class);

		}

	}

}