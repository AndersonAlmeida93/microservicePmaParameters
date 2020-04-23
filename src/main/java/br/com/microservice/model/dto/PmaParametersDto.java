package br.com.microservice.model.dto;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.microservice.model.enun.ActionEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
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

}
