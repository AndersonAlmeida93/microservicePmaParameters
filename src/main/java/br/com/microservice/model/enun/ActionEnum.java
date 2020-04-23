package br.com.microservice.model.enun;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ActionEnum {

	UPDATE("Update"), CANCEL("Cancel"), SUBMIT_ORDER("Submit_order");

	private String description;

}
