package br.com.microservice.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.criteria.Predicate;
import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import br.com.microservice.model.PmaParameters;
import br.com.microservice.model.dto.PmaParametersDto;
import br.com.microservice.model.dto.UpdatePmaParameters;
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
	

	@Override
	public List<PmaParametersDto> getPmaDtos(String partner, Integer reasonCode, String actionPma, String livpnr) {

		List<PmaParameters> pmas = pmaRepository.findAll((Specification<PmaParameters>) (root, cq, cb) -> {
			Predicate p = cb.conjunction();
			if (!StringUtils.isEmpty(partner)) {
				p = cb.and(p, cb.like(root.get("partner"), "%" + partner + "%"));
			}
			if (reasonCode != null) {
				p = cb.and(p, cb.like(root.get("reasonCode").as(String.class), "%" + reasonCode + "%"));
			}
			if (!StringUtils.isEmpty(actionPma)) {
				p = cb.and(p, cb.like(root.get("actionPma"), "%" + actionPma + "%"));
			}
			if (!StringUtils.isEmpty(livpnr)) {
				p = cb.and(p, cb.like(root.get("livpnr"), "%" + livpnr + "%"));
			}
			return p;
		});
		return pmas.stream().map(this::converToDto).collect(Collectors.toList());

	}

}
