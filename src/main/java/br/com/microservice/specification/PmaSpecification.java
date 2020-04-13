package br.com.microservice.specification;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import br.com.microservice.model.PmaParameters;
import br.com.microservice.model.dto.PmaParametersRequest;

@Component
public final class PmaSpecification {

	private static final String FIELD_PARTNER = "partner";
	private static final String FIELD_REASON_CODE = "reasonCode";
	private static final String FIELD_ACTION_PMA = "actionPma";
	private static final String FIELD_LIVPNR = "livpnr";

	private PmaSpecification() {

	}

	public static Specification<PmaParameters> findByParam(PmaParametersRequest filter) {

		return new Specification<PmaParameters>() {

			private static final long serialVersionUID = 1L;

			public Predicate toPredicate(Root<PmaParameters> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

				List<Predicate> predicates = new ArrayList<>();

				if (StringUtils.isNotEmpty(filter.getPartner())) {
					predicates.add(cb.equal(root.get(FIELD_PARTNER), filter.getPartner()));
				}

				if (filter.getReasonCode() != null) {
					predicates.add(cb.equal(root.get(FIELD_REASON_CODE), filter.getReasonCode()));
				}

				if (filter.getActionPma() != null) {
					predicates.add(cb.equal(root.get(FIELD_ACTION_PMA), filter.getActionPma()));
				}

				if (StringUtils.isNotEmpty(filter.getLivpnr())) {
					predicates.add(cb.equal(root.get(FIELD_LIVPNR), filter.getLivpnr()));
				}

				query.distinct(true);
				return cb.and(predicates.toArray(new Predicate[] {}));
			}

		};
	}

}
