package br.com.microservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import br.com.microservice.model.PmaParameters;

public interface PmaParametersRepository
		extends JpaRepository<PmaParameters, Integer>, JpaSpecificationExecutor<PmaParameters> {

	PmaParameters findActionPmaById(Integer id);
}
