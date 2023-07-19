package com.hbdev.carrossb;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.hbdev.carrossb.domain.Carro;
import com.hbdev.carrossb.domain.CarroDTO;
import com.hbdev.carrossb.domain.CarroService;
import static junit.framework.TestCase.*;

import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
class CarrosSbApplicationTests {

	@Autowired
	private CarroService carroService;

	@Test
	public void testSave() {
		Carro carro = new Carro();
        carro.setName("Poshi");
        carro.setTipo("esportivos");
       

        CarroDTO c = carroService.insert(carro);

        assertNotNull(c);
        Long id = c.getId();
        assertNotNull(id);

        // Buscar o objeto
        Optional<CarroDTO> op = carroService.getById(id);
        assertTrue(op.isPresent());

        c = op.get();
        assertEquals("Poshi",c.getName());
       assertEquals("esportivos",c.getTipo());

        // Deletar o objeto
        carroService.delete(id);

        // Verificar se deletou
       assertFalse(carroService.getById(id).isPresent());

	}

	@Test
	public void testLista() {
		
		List<CarroDTO> carros = carroService.getCarros();
		
		assertEquals(30, carros.size());
		
	}
	
//	@Test
//	public void testget() {
//		
//		Optional<CarroDTO> op = carroService.getById(1L);
//		
//		assertTrue(op.isPresent());
//		CarroDTO c = op.get();
//		
//		assertEquals("Camaro SS 1969", c.getName());
//		
//		
//	}

}
