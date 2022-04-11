package com.comerciante.galaxia.simbolos;

import java.io.Serializable;
import java.util.Map;

/*
 * Clase Simbolos que lleva el convenio de transformacion de numeros algebraicos y romanos
 */
public class Simbolos implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	private static Map<String, Long> mapaSimbolos;
	
	public Simbolos(){
		
	}
	
	@SuppressWarnings("static-access")
	public Simbolos(Map<String, Long> mapaSimbolos){
		this.mapaSimbolos = mapaSimbolos;
	}
	
	/*
	 *  getters and setters
	 */

	public static Map<String, Long> getMapaSimbolos() {
		return mapaSimbolos;
	}

	public static void setMapaSimbolos(Map<String, Long> mapaSimbolos) {
		Simbolos.mapaSimbolos = mapaSimbolos;
	}
	
	/*
	 * Metodo: getValorSimbolobyName
	 * Parametro: nombreSimbolo
	 * Descripcion: metodo que retorna el valor del simbolo
	 */
	@SuppressWarnings("static-access")
	public Long getValorSimbolobyName(String nombreSimbolo){
		return this.mapaSimbolos.get(nombreSimbolo);
	}

}
