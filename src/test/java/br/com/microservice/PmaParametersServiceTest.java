package br.com.microservice;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.microservice.model.PmaParameters;
import br.com.microservice.model.enun.ActionEnum;
import br.com.microservice.repository.PmaParametersRepository;
import br.com.microservice.service.PmaParametersServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
public class PmaParametersServiceTest {

	@InjectMocks
	private PmaParametersServiceImpl pmaServiceImpl;

	@Mock
	private PmaParametersRepository pmaRepository;

	@Before
	public void setup() {

		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testCreated() throws Exception {

		PmaParameters pma = new PmaParameters("AGSA", 8587, "description for partner AGSA", ActionEnum.UPDATE,
				"Seahawks", "teste AGSA");
		when(pmaRepository.save(pma)).thenReturn(pma);
	}

	@Test
	public void testDelete() throws Exception {

		PmaParameters pma = new PmaParameters("AGSA", 8587, "description for partner AGSA", ActionEnum.UPDATE,
				"Seahawks", "teste AGSA");
		pmaServiceImpl.delete(pma.getId());
		verify(pmaRepository, times(1)).deleteById(pma.getId());
	}

}
