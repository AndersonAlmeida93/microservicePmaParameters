package br.com.microservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import br.com.microservice.model.PmaParameters;

@Repository
public interface PmaParametersRepository extends JpaRepository<PmaParameters, Integer>, JpaSpecificationExecutor<PmaParameters> {

	PmaParameters findActionPmaById(Integer id);

	Page<PmaParameters> findAll(Specification<PmaParameters> criteria,Pageable page);
}
