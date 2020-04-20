package br.com.microservice.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.nio.file.Files;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.microservice.model.dto.PmaParametersDto;
import br.com.microservice.model.dto.PmaParametersRequest;
import br.com.microservice.model.dto.UpdatePmaParameters;
import br.com.microservice.model.enun.ActionEnum;

@SpringBootTest
@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PmaParametersServiceTest {

	@Mock
	private PmaParametersDto pmaDto;

	@InjectMocks
	@Autowired
	private PmaParametersServiceImpl pmaServiceImpl;

	@Test
	public void testCreated() throws Exception {

		File file = new ClassPathResource("json/CreatePmaParameterDTO.json").getFile();
		String jsonFile = new String(Files.readAllBytes(file.toPath()));

		ObjectMapper objmapper = new ObjectMapper();
		PmaParametersDto pmaDto = objmapper.readValue(jsonFile, PmaParametersDto.class);

		pmaServiceImpl.created(pmaDto);
		assertEquals("AGSA", pmaDto.getPartner());
		assertNotNull(pmaDto);

	}

	@Test
	public void testGetByFilter() throws Exception {

		PmaParametersRequest request = new PmaParametersRequest("", null, ActionEnum.UPDATE, "");
		pmaServiceImpl.getPmaDtos(request);

	}

	@Test
	public void testUpdated() throws Exception {

		PmaParametersDto pmaDto = new PmaParametersDto(2, "AGSA", 9999, "description of partner AGSA",
				ActionEnum.UPDATE, "teste", "value AGSA");
		File file = new ClassPathResource("json/UpdatePmaParameterDTO.json").getFile();
		String jsonFile = new String(Files.readAllBytes(file.toPath()));

		ObjectMapper objmapper = new ObjectMapper();
		UpdatePmaParameters updatePma = objmapper.readValue(jsonFile, UpdatePmaParameters.class);
		pmaServiceImpl.update(pmaDto.getId(), updatePma);
		assertEquals("new description", updatePma.getDescriptionCode());
	}

	@Test
	public void testWhenDelete() throws Exception {

		PmaParametersDto pmaDto = new PmaParametersDto(2, "AGSA", 9999, "description of partner AGSA",
				ActionEnum.UPDATE, "teste", "value AGSA");
		pmaServiceImpl.delete(pmaDto.getId());
	}

}
