package br.com.microservice.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.nio.file.Files;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.microservice.model.dto.PmaParametersDto;
import br.com.microservice.model.dto.PmaParametersRequest;
import br.com.microservice.model.dto.UpdatePmaParameters;

@SpringBootTest
@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PmaParametersServiceTest {

	@Mock
	private ModelMapper modelMapper;

	@Mock
	private PmaParametersDto pmaDto;

	@InjectMocks
	private PmaParametersServiceImpl pmaServiceImpl;

	@Before
	public void setup() {

		MockitoAnnotations.initMocks(this);
	}

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

		PmaParametersRequest request = new PmaParametersRequest();
		pmaServiceImpl.getPmaDtos(request);
		assertThat(request);

	}

	@Test
	public void testUpdated() throws Exception {

		PmaParametersDto pmaDto = new PmaParametersDto();
		pmaDto.setId(1);
		UpdatePmaParameters request = new UpdatePmaParameters("new description", "new value");
		pmaServiceImpl.update(1, request);
	}

	@Test
	public void testWhenDelete() throws Exception {

		PmaParametersDto pmaDto = new PmaParametersDto();
		pmaDto.setId(1);
		pmaServiceImpl.delete(1);
	}

}
