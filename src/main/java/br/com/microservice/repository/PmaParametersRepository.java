package br.com.microservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.microservice.model.PmaParameters;
import br.com.microservice.model.enun.ActionEnum;

public interface PmaParametersRepository extends JpaRepository<PmaParameters, Integer> {

	List<PmaParameters> findByPartnerIgnoreCase(String partner);

	List<PmaParameters> findByReasonCode(Integer reasonCode);

	List<PmaParameters> findByActionPma(ActionEnum action);

	@Query("select p from pma_parameters p where lower (p.livpnr) like (concat('%', :livpnr, '%'))")
	List<PmaParameters> findByLivpnr(@Param("livpnr") String livpnr);
	
	PmaParameters findActionPmaById(Integer id);
}
