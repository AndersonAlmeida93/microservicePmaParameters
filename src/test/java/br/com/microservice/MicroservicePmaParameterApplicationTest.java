package br.com.microservice;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;

@SpringBootTest
public class MicroservicePmaParameterApplicationTest {

	@Test
	void contextLoads() {
	}

	@Bean
	public ModelMapper modelMapper() {

		return new ModelMapper();
	}

}
