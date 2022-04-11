package com.comerciante.galaxia.preguntas;

import java.util.ArrayList;
import java.util.List;


public enum TiposPreguntas {

	HOW_MUCH_QUESTION("HOW MUCH IS"),
	HOW_MANY_QUESTION("HOW MANY CREDITS IS");
	
	String preguntaString;

	private TiposPreguntas(String questionString) {
		this.preguntaString = questionString;
	}
		
	
	public String getPreguntaString() {
		return preguntaString;
	}

	public static List<String> getValidQuestionValues(){
		List<String> tiposPreguntas = new ArrayList<String>();
		for(TiposPreguntas tiposPregunta : TiposPreguntas.values()){
			tiposPreguntas.add(tiposPregunta.getPreguntaString());
		}
		
		return tiposPreguntas;
	}
}
