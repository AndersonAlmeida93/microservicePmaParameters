package br.com.microservice.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.io.File;
import java.nio.file.Files;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.microservice.model.PmaParameters;
import br.com.microservice.model.dto.PmaParametersDto;
import br.com.microservice.model.dto.PmaParametersRequest;
import br.com.microservice.model.dto.UpdatePmaParameters;
import br.com.microservice.model.enun.ActionEnum;
import br.com.microservice.repository.PmaParametersRepository;

@SpringBootTest
@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestPropertySource(locations = "classpath:application.yml")
public class PmaParametersServiceTest {

	@Mock
	private PmaParametersDto pmaDto;

	@Autowired
	private PmaParametersRepository pmaRepository;

	@InjectMocks
	@Autowired
	private PmaParametersServiceImpl pmaServiceImpl;

	@Before
	public void setUp() {

		pmaRepository.save(new PmaParameters(1, "AGSA", 0110, "description of partner AGSA", ActionEnum.UPDATE, "teste",
				"value AGSA"));
		pmaRepository.save(new PmaParameters(2, "LOSA", 0315, "description of partner LOSA", ActionEnum.CANCEL, "teste",
				"value LOSA"));
		pmaRepository.save(new PmaParameters(3, "CASA", 1903, "description of partner CASA", ActionEnum.UPDATE, "teste",
				"value CASA"));
		pmaRepository.save(new PmaParameters(4, "VCS", 2501, "description of partner VCS", ActionEnum.SUBMIT_ORDER,
				"teste", "value VCS"));
	}

	@After
	public void tearDown() {

		pmaRepository.deleteAll();
	}

	@Test
	public void testCreated() throws Exception {

		File file = new ClassPathResource("json/CreatePmaParameterDTO.json").getFile();
		String jsonFile = new String(Files.readAllBytes(file.toPath()));

		ObjectMapper objmapper = new ObjectMapper();
		PmaParametersDto pmaDto = objmapper.readValue(jsonFile, PmaParametersDto.class);

		PmaParametersDto result = pmaServiceImpl.created(pmaDto);
		assertNotNull(result.getId());
		assertEquals("AGSA", result.getPartner());

	}

	/**
	 * Método para listar os PmaParameters, poderá optar por listar todos, ou listar
	 * por Partner,ReasonCode, ActionPma e Livpnr
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetByFilter() throws Exception {

		PmaParametersRequest request = new PmaParametersRequest();

		List<PmaParametersDto> result = pmaServiceImpl.getPmaDtos(request);
		assertEquals(4, result.size());

		PmaParametersRequest requestPartner = new PmaParametersRequest("AGSA", null, null, null);

		List<PmaParametersDto> resultPartner = pmaServiceImpl.getPmaDtos(requestPartner);
		assertEquals(1, resultPartner.size());

		PmaParametersRequest requestReasonCode = new PmaParametersRequest(null, 0315, null, null);

		List<PmaParametersDto> resultReasonCode = pmaServiceImpl.getPmaDtos(requestReasonCode);
		assertEquals(1, resultReasonCode.size());

		PmaParametersRequest requestActionPma = new PmaParametersRequest(null, null, ActionEnum.SUBMIT_ORDER, null);

		List<PmaParametersDto> resultActionPma = pmaServiceImpl.getPmaDtos(requestActionPma);
		assertEquals(1, resultActionPma.size());

		PmaParametersRequest requestLivpnr = new PmaParametersRequest(null, null, null, "teste");

		List<PmaParametersDto> resultLivpnr = pmaServiceImpl.getPmaDtos(requestLivpnr);
		assertEquals(4, resultLivpnr.size());

	}

	/**
	 * Método para atulizar o PmaParameters,o dado da coluna 'value' só será
	 * atualizado caso o ActionPma = UPDATE
	 * 
	 * @throws Exception
	 */
	@Test
	public void testUpdated() throws Exception {

		File file = new ClassPathResource("json/UpdatePmaParameterDTO.json").getFile();
		String jsonFile = new String(Files.readAllBytes(file.toPath()));

		ObjectMapper objmapper = new ObjectMapper();
		UpdatePmaParameters updatePma = objmapper.readValue(jsonFile, UpdatePmaParameters.class);
		PmaParametersDto resultUpdate = pmaServiceImpl.update(10, updatePma);
		assertEquals("new description", resultUpdate.getDescriptionCode());
		assertEquals("new value", resultUpdate.getValue());

		PmaParametersDto result = pmaServiceImpl.update(11, updatePma);
		assertEquals("new description", result.getDescriptionCode());
		assertNotEquals("new value", result.getValue());

	}

	@Test
	public void testWhenDelete() throws Exception {

		boolean result = pmaServiceImpl.delete(14);
		assertEquals(true, result);

	}

}
