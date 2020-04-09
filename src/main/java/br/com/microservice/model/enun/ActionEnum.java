package br.com.microservice.model.enun;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public enum ActionEnum {

	UPDATE("Update"),
	CANCEL ("Cancel"),
	SUBMIT_ORDER("Submit_order");
	
	private String description;
	
}
