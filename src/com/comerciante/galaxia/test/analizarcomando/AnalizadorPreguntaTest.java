package com.comerciante.galaxia.test.analizarcomando;

import static org.junit.Assert.fail;

import org.junit.Assert;
import org.junit.Test;

import com.comerciante.galaxia.analizarcomando.AnalizadorPregunta;
import com.comerciante.galaxia.pregunta.interfaz.IPregunta;

public class AnalizadorPreguntaTest {

	@Test
	public void testAnalizadorPregunta() {
		AnalizadorPregunta analizadorPregunta = new AnalizadorPregunta("How much is something of something?");
		IPregunta pregunta = analizadorPregunta.toPregunta();

        Assert.assertTrue(pregunta instanceof com.comerciante.galaxia.pregunta.implentar.HowMuchQuestion);
		
	}

	@Test
	public void testToPregunta() {
		fail("Not yet implemented");
	}

}
