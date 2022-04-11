package com.comerciante.galaxia.respuestas;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

/*
 * Clase Respuestas permite almacenar preguntas y repuestas 
 */
public class Respuestas implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Map<String, String> mapaPreguntasRespuestas = new LinkedHashMap<String, String>();
	
	//getters and setters
	public Map<String, String> getMapaPreguntasRespuestas() {
		return mapaPreguntasRespuestas;
	}

	public void setMapaPreguntasRespuestas(Map<String, String> mapaPreguntasRespuestas) {
		this.mapaPreguntasRespuestas = mapaPreguntasRespuestas;
	}

	// añadimos al mapa las preguntas y respuestas 
	public void anadirPreguntasRespuestas(String preguntas, String respuestas){
		mapaPreguntasRespuestas.put(preguntas, respuestas);		
	}

	
} 
