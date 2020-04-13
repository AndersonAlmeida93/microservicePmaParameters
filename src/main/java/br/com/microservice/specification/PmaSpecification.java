package br.com.microservice.specification;

import java.util.List;

import javax.persistence.criteria.Predicate;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import br.com.microservice.model.PmaParameters;
import br.com.microservice.model.enun.ActionEnum;
import br.com.microservice.repository.PmaParametersRepository;

@Component
public final class PmaSpecification {

	@Autowired
	private PmaParametersRepository pmaRepository;

	public List<PmaParameters> getPmaDtos(String partner, Integer reasonCode, ActionEnum actionPma, String livpnr) {

		List<PmaParameters> pmas = pmaRepository.findAll((Specification<PmaParameters>) (root, cq, cb) -> {

			Predicate p = cb.conjunction();
			if (!StringUtils.isEmpty(partner)) {
				p = cb.and(p, cb.equal(root.get("partner"), partner));
			}
			if (reasonCode != null) {
				p = cb.and(p, cb.equal(root.get("reasonCode"),reasonCode));
			}
			if (actionPma != null) {
				p = cb.and(p, cb.equal(root.get("actionPma"), actionPma));
			}
			if (!StringUtils.isEmpty(livpnr)) {
				p = cb.and(p, cb.equal(root.get("livpnr"), livpnr));
			}
			return p;
		});

		return pmas;
	}
}
