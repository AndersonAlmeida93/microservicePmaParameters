package br.com.microservice.model.dto;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.microservice.model.enun.ActionEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PmaParametersDto {

	private Integer id;

	@NotEmpty(message = "can't be empty")
	@Length(min = 1, max = 5, message = "the length must be between 1 and 5 characters")
	private String partner;

	@NotNull
	private Integer reasonCode;

	@NotEmpty(message = "can't be empty")
	@Length(min = 1, max = 150, message = "the length must be between 1 and 150 characters")
	private String descriptionCode;

	@NotNull
	@Enumerated(EnumType.STRING)
	private ActionEnum actionPma;

	@Length(min = 1, max = 20, message = "the length must be between 1 and 20 characters")
	private String livpnr;

	@Length(min = 1, max = 20, message = "the length must be between 1 and 20 characters")
	private String value;

	public PmaParametersDto(String partner, Integer reasonCode, String descriptionCode, ActionEnum actionPma,
			String livpnr, String value) {
		this.partner = partner;
		this.reasonCode = reasonCode;
		this.descriptionCode = descriptionCode;
		this.actionPma = actionPma;
		this.livpnr = livpnr;
		this.value = value;
	}

}
