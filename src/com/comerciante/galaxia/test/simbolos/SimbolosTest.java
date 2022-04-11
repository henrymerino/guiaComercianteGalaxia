package com.comerciante.galaxia.test.simbolos;

import static org.junit.Assert.fail;

import org.junit.Test;

import com.comerciante.galaxia.convenio.GalaxiaConvenio;
import com.comerciante.galaxia.simbolos.Simbolos;

import junit.framework.Assert;

@SuppressWarnings("deprecation")
public class SimbolosTest {

	@Test
	public void testGetMapaSimbolos() {
		fail("Not yet implemented");
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testGetValorSimbolobyName() {
		GalaxiaConvenio galaxiaConvenio = new GalaxiaConvenio();
		@SuppressWarnings("static-access")
		Simbolos simbolos = new Simbolos(galaxiaConvenio.getGalaxiaSimbos());
		
		long valorSimbolos = simbolos.getValorSimbolobyName("X");
		System.out.println("valor " + valorSimbolos);
		Assert.assertEquals(valorSimbolos, 10L);
	}

}
