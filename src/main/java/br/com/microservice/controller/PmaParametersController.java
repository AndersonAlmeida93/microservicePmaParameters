package br.com.microservice.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.microservice.model.dto.PmaParametersDto;
import br.com.microservice.model.dto.PmaParametersRequest;
import br.com.microservice.model.dto.UpdatePmaParameters;
import br.com.microservice.service.PmaParametersServiceImpl;

@RestController
@RequestMapping("/api/v1/parameters")
public class PmaParametersController {

	@Autowired
	private PmaParametersServiceImpl pmaServiceImpl;

	@PostMapping
	public ResponseEntity<PmaParametersDto> created(@RequestBody @Valid PmaParametersDto pmaDto) {

		PmaParametersDto newPmaDto = pmaServiceImpl.created(pmaDto);
		return ResponseEntity.ok().body(newPmaDto);
	}

	@GetMapping
	public ResponseEntity<List<PmaParametersDto>> getPmas(PmaParametersRequest request) {

		List<PmaParametersDto> pmas = pmaServiceImpl.getPmaDtos(request);
		return ResponseEntity.ok().body(pmas);
	}

	@PatchMapping("/{id}")
	public ResponseEntity<PmaParametersDto> update(@PathVariable Integer id,
			@Valid @RequestBody UpdatePmaParameters updateParameters) {

		PmaParametersDto update = pmaServiceImpl.update(id, updateParameters);
		return ResponseEntity.ok().body(update);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Integer id) {

		pmaServiceImpl.delete(id);
	}

}
