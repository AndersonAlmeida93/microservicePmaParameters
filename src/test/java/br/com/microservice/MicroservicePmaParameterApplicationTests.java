package br.com.microservice;

import java.net.URI;
import java.net.URISyntaxException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.microservice.model.PmaParameters;
import br.com.microservice.model.dto.UpdatePmaParameters;
import br.com.microservice.model.enun.ActionEnum;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class MicroservicePmaParameterApplicationTests {

	@Autowired
	private TestRestTemplate restTemplate;

	@LocalServerPort
	int randomServerPort;

	@Before
	public void setup() {
		restTemplate.getRestTemplate().setRequestFactory(new HttpComponentsClientHttpRequestFactory());
	}

	@Test
	public void testCreatedPma() throws URISyntaxException {

		final String baseUrl = "http://localhost:" + randomServerPort + "/parameters";
		URI uri = new URI(baseUrl);
		PmaParameters pma = new PmaParameters("LFSS", 8587, "description for partner LFSS", ActionEnum.UPDATE,
				"Nautico", "teste LFSS");

		HttpHeaders headers = new HttpHeaders();

		HttpEntity<PmaParameters> httpEntity = new HttpEntity<>(pma, headers);

		ResponseEntity<String> responseEntity = this.restTemplate.postForEntity(uri, httpEntity, String.class);

		Assert.assertEquals(200, responseEntity.getStatusCodeValue());
	}

	@Test
	public void testUpdatePma() throws URISyntaxException {

		String baseUrl = "http://localhost:" + randomServerPort + "/parameters/?";
		URI uri = new URI(baseUrl);
		UpdatePmaParameters updatePma = new UpdatePmaParameters("new description VSC", "new value for VSC");

		HttpHeaders headers = new HttpHeaders();

		HttpEntity<UpdatePmaParameters> httpEntity = new HttpEntity<>(updatePma, headers);

		ResponseEntity<String> responseEntity = this.restTemplate.exchange(uri, HttpMethod.PATCH, httpEntity,
				String.class);

		Assert.assertEquals(200, responseEntity.getStatusCodeValue());

	}

	@Test
	public void testDeletePma() throws URISyntaxException {

		String baseUrl = "http://localhost:" + randomServerPort + "/parameters/?";
		URI uri = new URI(baseUrl);

		HttpHeaders headers = new HttpHeaders();

		HttpEntity<PmaParameters> httpEntity = new HttpEntity<>(headers);

		ResponseEntity<String> responseEntity = this.restTemplate.exchange(uri, HttpMethod.DELETE, httpEntity,
				String.class);

		Assert.assertEquals(200, responseEntity.getStatusCodeValue());

	}

	@Test
	public void testGetPma() throws URISyntaxException {

		String baseUrl = "http://localhost:" + randomServerPort + "/parameters?reasonCode=8587";
		URI uri = new URI(baseUrl);

		ResponseEntity<PmaParameters[]> responseEntity = this.restTemplate.getForEntity(uri, PmaParameters[].class);

		PmaParameters[] pma = responseEntity.getBody();
		System.out.println(pma);

		Assert.assertEquals(200, responseEntity.getStatusCodeValue());
	}

}
