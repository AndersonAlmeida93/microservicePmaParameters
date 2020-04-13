package br.com.microservice.repository;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import br.com.microservice.model.PmaParameters;

public interface PmaParametersRepository extends JpaRepository<PmaParameters, Integer>, JpaSpecificationExecutor<PmaParameters> {

	PmaParameters findActionPmaById(Integer id);

	List<PmaParameters> findAll(Specification<PmaParameters> criteria);
}
