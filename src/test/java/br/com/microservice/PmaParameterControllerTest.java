package br.com.microservice;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.nio.file.Files;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import br.com.microservice.controller.PmaParametersController;
import br.com.microservice.model.dto.PmaParametersDto;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class PmaParameterControllerTest {

	private static final String BASE_URL = "/api/v1/parameters";

	PmaParametersDto pmaDto;

	@Autowired
	@InjectMocks
	private PmaParametersController pmaParametersController;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext wac;

	@Before
	public void setup() {

		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@Test
	public void testCreatedPma() throws Exception {

		String URI = BASE_URL;

		File file = new ClassPathResource("json/CreatePmaParameterDTO.json").getFile();
		String jsonFile = new String(Files.readAllBytes(file.toPath()));

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(URI).accept(MediaType.APPLICATION_JSON)
				.content(jsonFile).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}

	@Test
	public void testUpdatePma() throws Exception {

		String URI = BASE_URL;

		File file = new ClassPathResource("json/UpdatePmaParameterDTO.json").getFile();
		String jsonFile = new String(Files.readAllBytes(file.toPath()));

		RequestBuilder requestBuilder = MockMvcRequestBuilders.patch(URI + "/" + 12).accept(MediaType.APPLICATION_JSON)
				.content(jsonFile).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}

	@Test
	public void testDeletePma() throws Exception {

		String URI = BASE_URL;

		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete(URI + "/" + 15).accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.OK.value(), response.getStatus());

	}

	@Test
	public void testGetPmaByFilter() throws Exception {

		String URI = obterUrlCompleta();

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}

	private String obterUrlCompleta() {

		StringBuilder url = new StringBuilder();
		url.append(BASE_URL);
		url.append("?partner=AGSA");
		url.append("&reasonCode=");
		url.append("&actionPma=");
		url.append("&livpnr=");

		return url.toString();

	}

}
