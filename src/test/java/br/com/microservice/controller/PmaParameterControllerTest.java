package br.com.microservice.controller;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.nio.file.Files;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
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

import br.com.microservice.model.dto.PmaParametersDto;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PmaParameterControllerTest {

	private static final String BASE_URL = "/api/v1/parameters";

	private static final Integer DELETE_ID = 1000;
	private static final Integer UPDATE_ID = 1000;

	PmaParametersDto pmaDto;

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
	public void testCreated() throws Exception {

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
	public void testCreatedIllegalArguments() throws Exception {

		String URI = BASE_URL;

		File file = new ClassPathResource("json/CreatePmaParameterIllegalArgumentsDTO.json").getFile();
		String jsonFile = new String(Files.readAllBytes(file.toPath()));

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(URI).accept(MediaType.APPLICATION_JSON)
				.content(jsonFile).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
	}

	@Test
	public void testGetPmas() throws Exception {

		String URI = obterUrlCompleta();

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}

	@Test
	public void testUpdate() throws Exception {

		String URI = BASE_URL;

		File file = new ClassPathResource("json/UpdatePmaParameterDTO.json").getFile();
		String jsonFile = new String(Files.readAllBytes(file.toPath()));

		RequestBuilder requestBuilder = MockMvcRequestBuilders.patch(URI + "/" + 1).accept(MediaType.APPLICATION_JSON)
				.content(jsonFile).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}

	@Test
	public void testUpdateNotFound() throws Exception {

		String URI = BASE_URL;

		File file = new ClassPathResource("json/UpdatePmaParameterDTO.json").getFile();
		String jsonFile = new String(Files.readAllBytes(file.toPath()));

		RequestBuilder requestBuilder = MockMvcRequestBuilders.patch(URI + "/" + UPDATE_ID)
				.accept(MediaType.APPLICATION_JSON).content(jsonFile).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
	}

	@Test
	public void testUpdateInvalidId() throws Exception {

		String URI = BASE_URL;

		File file = new ClassPathResource("json/UpdatePmaParameterDTO.json").getFile();
		String jsonFile = new String(Files.readAllBytes(file.toPath()));

		RequestBuilder requestBuilder = MockMvcRequestBuilders.patch(URI + "/" + null)
				.accept(MediaType.APPLICATION_JSON).content(jsonFile).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
	}

	@Test
	public void testWhenDelete() throws Exception {

		String URI = BASE_URL;

		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete(URI + "/" + 1).accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.OK.value(), response.getStatus());

	}

	@Test
	public void testDeleteNotFound() throws Exception {
		String URI = BASE_URL;

		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete(URI + "/" + DELETE_ID)
				.accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
	}

	@Test
	public void testDeleteInvalidId() throws Exception {
		String URI = BASE_URL;

		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete(URI + "/" + 0).accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
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
