package br.com.microservice.model.dto;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdatePmaParameters {

	@NotEmpty(message = "can't be empty")
	@Length(min = 1, max = 150, message = "the length must be between 1 and 150 characters")
	private String descriptionCode;

	@Length(min = 1, max = 20, message = "the length must be between 1 and 20 characters")
	private String value;
}
