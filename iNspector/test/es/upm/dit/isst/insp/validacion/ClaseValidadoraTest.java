package es.upm.dit.isst.insp.validacion;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ClaseValidadoraTest {

	@BeforeEach
	void setUp() throws Exception {
		//se ejecuta antes de cada test
	}

	@AfterEach
	void tearDown() throws Exception {
		//se ejecuta despues de cada test
	}

	/**
	 * Comprueba que el email sigue el formato caracteres + @ + caracteres + . + caracteres
	 */
	@Test
	void testCompruebaEmail_Formato() {
		
		//Emails no admitidos
		assertFalse(ClaseValidadora.compruebaEmail("email.com"),"Email no valido");
		assertFalse(ClaseValidadora.compruebaEmail("email@@email.com"),"Email no valido");
		assertFalse(ClaseValidadora.compruebaEmail("email@email@email.com"),"Email no valido");
		assertFalse(ClaseValidadora.compruebaEmail("@email.com"),"Email no valido");
		assertFalse(ClaseValidadora.compruebaEmail("@"),"Email no valido");
		assertFalse(ClaseValidadora.compruebaEmail("email@"),"Email no valido");
		assertFalse(ClaseValidadora.compruebaEmail("$$%@email.com"),"Email no valido");
		assertFalse(ClaseValidadora.compruebaEmail("email@##€>.com"),"Email no valido");
		assertFalse(ClaseValidadora.compruebaEmail("email@email"),"Email no valido");
		assertFalse(ClaseValidadora.compruebaEmail("email@email.1234"),"Email no valido");
		assertFalse(ClaseValidadora.compruebaEmail("email@email."),"Email no valido");
		assertFalse(ClaseValidadora.compruebaEmail(""),"Email no valido");
		assertFalse(ClaseValidadora.compruebaEmail(" "),"Email no valido");
		assertFalse(ClaseValidadora.compruebaEmail("email email@email.com"),"Email no valido");
		
		//Emails admitidos
		assertTrue(ClaseValidadora.compruebaEmail("email@email.com"),"Email valido");
		assertTrue(ClaseValidadora.compruebaEmail("email1234@email.es"),"Email valido");
		assertTrue(ClaseValidadora.compruebaEmail("email@alumnos.upm.es"),"Email valido");
		assertTrue(ClaseValidadora.compruebaEmail("email_email@email.com"),"Email valido");
		assertTrue(ClaseValidadora.compruebaEmail("email@hotmail.pl"),"Email valido");
		assertTrue(ClaseValidadora.compruebaEmail("email543email@email1234.com"),"Email valido");
		assertTrue(ClaseValidadora.compruebaEmail("email.email@email.com"),"Email valido");
		assertTrue(ClaseValidadora.compruebaEmail("email-email@email.com"),"Email valido");
		assertTrue(ClaseValidadora.compruebaEmail("email@email-email.com"),"Email valido");
		
	}

	@Test
	void testCompruebaCIF() {
		
		//CIF no admitido
		assertFalse(ClaseValidadora.compruebaCIF("12345678"),"CIF no valido");
		assertFalse(ClaseValidadora.compruebaCIF("12345678N"),"CIF no valido");
		assertFalse(ClaseValidadora.compruebaCIF("J12345678"),"CIF no valido");
		assertFalse(ClaseValidadora.compruebaCIF("7890"),"CIF no valido");
		assertFalse(ClaseValidadora.compruebaCIF("NEANDHST"),"CIF no valido");
		assertFalse(ClaseValidadora.compruebaCIF(""),"CIF no valido");
		assertFalse(ClaseValidadora.compruebaCIF(" "),"CIF no valido");
		assertFalse(ClaseValidadora.compruebaCIF("1234 678Z"),"CIF no valido");
		assertFalse(ClaseValidadora.compruebaCIF("123456789123456789"),"CIF no valido");
		
		//CIF admitidos: son todos aquellos que cumplen el formato del CIF (8 digitos y su correspondiente letra)
		//assertTrue(ClaseValidadora.compruebaCIF(""),"CIF valido");
		assertTrue(ClaseValidadora.compruebaCIF("12345678Z"),"CIF no valido");
		
	}

}
