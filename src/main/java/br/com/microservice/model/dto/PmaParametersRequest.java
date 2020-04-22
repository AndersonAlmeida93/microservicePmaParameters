package br.com.microservice.model.dto;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import br.com.microservice.model.enun.ActionEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PmaParametersRequest {

	private String partner;
	private Integer reasonCode;

	@Enumerated(EnumType.STRING)
	private ActionEnum actionPma;

	private String livpnr;
}
