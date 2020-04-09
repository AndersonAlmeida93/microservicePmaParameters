package br.com.microservice.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.microservice.model.dto.PmaParametersDto;
import br.com.microservice.service.PmaParametersServiceImpl;

@RestController
@RequestMapping("/parameters")
public class PmaParametersController {

	@Autowired
	private PmaParametersServiceImpl pmaServiceImpl;

	@PostMapping
	public ResponseEntity<PmaParametersDto> created(@RequestBody @Valid PmaParametersDto pmaDto) {

		PmaParametersDto newPmaDto = pmaServiceImpl.created(pmaDto);
		return ResponseEntity.ok().body(newPmaDto);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Integer id) {

		pmaServiceImpl.delete(id);
	}

	@GetMapping
	public ResponseEntity<List<PmaParametersDto>> getPmas(@RequestParam(required = false) String partner,
			@RequestParam(required = false) Integer reasonCode, @RequestParam(required = false) String actionPma,
			@RequestParam(required = false) String livpnr) {

		List<PmaParametersDto> pmas = pmaServiceImpl.getPmaDtos(partner, reasonCode, actionPma, livpnr);
		return ResponseEntity.ok().body(pmas);
	}

}
