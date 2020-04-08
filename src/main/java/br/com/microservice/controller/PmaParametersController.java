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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.microservice.model.dto.PmaParametersDto;
import br.com.microservice.model.dto.UpdatePmaParameters;
import br.com.microservice.model.enun.ActionEnum;
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

	@GetMapping
	public ResponseEntity<List<PmaParametersDto>> listAll() {

		List<PmaParametersDto> pmas = pmaServiceImpl.listAll();
		return ResponseEntity.ok().body(pmas);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Integer id) {

		pmaServiceImpl.delete(id);
	}

	@GetMapping("/partner")
	public ResponseEntity<List<PmaParametersDto>> listByPartner(@RequestParam String partner) {

		List<PmaParametersDto> pmas = pmaServiceImpl.listByPartners(partner);
		return ResponseEntity.ok().body(pmas);
	}

	@GetMapping("/reasoncode")
	public ResponseEntity<List<PmaParametersDto>> listBytReasonCode(@RequestParam Integer reasonCode) {

		List<PmaParametersDto> pmas = pmaServiceImpl.listByReasonCode(reasonCode);
		return ResponseEntity.ok().body(pmas);
	}

	@GetMapping("/action")
	public ResponseEntity<List<PmaParametersDto>> listByAction(@RequestParam("action") ActionEnum action) {

		List<PmaParametersDto> pmas = pmaServiceImpl.listByAction(action);
		return ResponseEntity.ok().body(pmas);
	}

	@GetMapping("/livpnr")
	public ResponseEntity<List<PmaParametersDto>> listByLivpnr(@RequestParam String livpnr) {

		List<PmaParametersDto> pmas = pmaServiceImpl.listByLivpnr(livpnr);
		return ResponseEntity.ok().body(pmas);
	}

	@PatchMapping("/{id}")
	public ResponseEntity<PmaParametersDto> update(@PathVariable Integer id,
			@Valid @RequestBody UpdatePmaParameters updateParameters) {

		PmaParametersDto update = pmaServiceImpl.update(id, updateParameters);
		return ResponseEntity.ok().body(update); 
	}
}
