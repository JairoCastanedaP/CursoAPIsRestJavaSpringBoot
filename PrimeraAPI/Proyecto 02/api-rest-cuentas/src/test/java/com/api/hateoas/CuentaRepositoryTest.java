package com.api.hateoas;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.api.hateoas.model.Cuenta;
import com.api.hateoas.repository.CuentaRepository;

@DataJpaTest
@Rollback(value=true)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)

public class CuentaRepositoryTest {

	
	@Autowired	
	private CuentaRepository cuentaRepository;
	
	@Test
	void restAgregarCuenta()
	{
		Cuenta cuenta= new Cuenta(1,"100");
		Cuenta cuentaGuardada = cuentaRepository.save(cuenta);
		
		Assertions.assertThat(cuentaGuardada).isNotNull();
		Assertions.assertThat(cuentaGuardada.getId()).isGreaterThan(0);
	}
}
