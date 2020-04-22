package br.com.microservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import br.com.microservice.model.enun.ActionEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "pma_parameters")
public class PmaParameters {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotNull
	private String partner;

	@NotNull
	@Column(name = "reason_code")
	private Integer reasonCode;

	@NotNull
	@Column(name = "description_code")
	private String descriptionCode;

	@NotNull
	@Column(name = "action_pma")
	@Enumerated(EnumType.STRING)
	private ActionEnum actionPma;

	private String livpnr;

	private String value;

}
