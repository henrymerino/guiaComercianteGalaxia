package com.comerciante.galaxia.reglas;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.comerciante.galaxia.convenio.GalaxiaConvenio;
import com.comerciante.galaxia.excepciones.ReglasVioladasExcepcion;
import com.comerciante.galaxia.excepciones.SimboloDesconocidoExcepcion;
import com.comerciante.galaxia.simbolos.Simbolos;


/*
 * Clase Reglas posee las regla de negocio 
 */
public class Reglas {
	
private static final List<String> LETRAS_NO_REPETIDAS = Arrays.asList("D", "L", "V");
	
	private static final List<String> LETRAS_REPETIDAS = Arrays.asList("I", "X", "M", "C");
	
	private static final int MAX_LETRAS_REPETIDAS = 3;
	
	private static final int MAX_LETRAS_NO_REPETIDAS = 1;

	
	public static Map<String, Integer> contadorLetrasRepetidas = getContadorLetrasRepetidas();
	
	public static Map<String, Integer> contadorLetrasNoRepetidas = getContadorLetrasNoRepetidas();


	public static Map<String, Integer> getContadorLetrasRepetidas() {
		@SuppressWarnings("serial")
		Map<String, Integer> map = new HashMap<String, Integer>() {
			{
				put(GalaxiaConvenio.simbolosValidos.I.toString(), 0);
				put(GalaxiaConvenio.simbolosValidos.X.toString(), 0);
				put(GalaxiaConvenio.simbolosValidos.C.toString(), 0);
				put(GalaxiaConvenio.simbolosValidos.M.toString(), 0);
			}
		};
		return map;
	}
	
	public static Map<String, Integer> getContadorLetrasNoRepetidas() {
		@SuppressWarnings("serial")
		Map<String, Integer> map = new HashMap<String, Integer>() {
			{
				put(GalaxiaConvenio.simbolosValidos.D.toString(), 0);
				put(GalaxiaConvenio.simbolosValidos.L.toString(), 0);
				put(GalaxiaConvenio.simbolosValidos.V.toString(), 0);
			}
		};
		return map;
	}
	
	

	public static Boolean isValido(String comando) {
		return null;
	}
	
	public Long calcularValor(Simbolos simbolos, String numRomano) throws SimboloDesconocidoExcepcion, ReglasVioladasExcepcion {
		if(numRomano == null){
			return null;
		}
		Long decimal = 0L;
		Long lastNumber = 0L;
		for (int i = numRomano.length() - 1; i >= 0; i--) {
			char ch = numRomano.charAt(i);
			String symbol = String.valueOf(ch);
			if(GalaxiaConvenio.simbolosValidos.getValidSymbol(symbol) == null){
				throw new SimboloDesconocidoExcepcion("Simbolo desconocido encontrado : "+symbol);
			}
			if(LETRAS_REPETIDAS.contains(symbol) && contadorLetrasRepetidas.get(symbol) <= MAX_LETRAS_REPETIDAS){
				contadorLetrasRepetidas.put(symbol, contadorLetrasRepetidas.get(symbol)+1);
				decimal = validarRomano(
						simbolos.getValorSimbolobyName(symbol),
						lastNumber, decimal);
				lastNumber = simbolos.getValorSimbolobyName(symbol);
			}else if(LETRAS_NO_REPETIDAS.contains(symbol) && contadorLetrasNoRepetidas.get(symbol) <= MAX_LETRAS_NO_REPETIDAS){
				contadorLetrasNoRepetidas.put(symbol, contadorLetrasNoRepetidas.get(symbol)+1);
				decimal = validarRomano(
						simbolos.getValorSimbolobyName(symbol),
						lastNumber, decimal);
				lastNumber = simbolos.getValorSimbolobyName(symbol);
			}else
				throw new ReglasVioladasExcepcion("Número máximo de letras excedidas para "+ symbol);
			}
		contadorLetrasRepetidas = getContadorLetrasRepetidas();
		
		contadorLetrasNoRepetidas = getContadorLetrasNoRepetidas();
		return decimal;

	}
		

	private Long validarRomano(Long TotalDecimal, Long ultimaLetraRomana,
			Long ultimoDecimal) {
		if (ultimaLetraRomana > TotalDecimal) {
			return ultimoDecimal - TotalDecimal;
		} else {
			return ultimoDecimal + TotalDecimal;
		}

	}

}
