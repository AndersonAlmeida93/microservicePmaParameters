package br.com.microservice.model.dto;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import br.com.microservice.model.enun.ActionEnum;
import lombok.Data;

@Data
public class PmaParametersRequest {

	private String partner;
	private Integer reasonCode;

	@Enumerated(EnumType.STRING)
	private ActionEnum actionPma;

	private String livpnr;
}
