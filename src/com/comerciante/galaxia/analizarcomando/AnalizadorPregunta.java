package com.comerciante.galaxia.analizarcomando;

import java.util.HashMap;
import java.util.Map;

import com.comerciante.galaxia.pregunta.implentar.HowManyQuestion;
import com.comerciante.galaxia.pregunta.implentar.HowMuchQuestion;
import com.comerciante.galaxia.pregunta.implentar.PreguntaIndefinida;
import com.comerciante.galaxia.pregunta.interfaz.IPregunta;
import com.comerciante.galaxia.preguntas.TiposPreguntas;


public class AnalizadorPregunta {

	private static final int PREGUNTA_EMPIEZA_INDEX = 0;

    @SuppressWarnings("serial")
	private static Map<String, IPregunta> mapaPreguntas = new HashMap<String, IPregunta>() {{
        put(TiposPreguntas.HOW_MUCH_QUESTION.getPreguntaString().toUpperCase(), new HowMuchQuestion());
        put(TiposPreguntas.HOW_MANY_QUESTION.getPreguntaString().toUpperCase(), new HowManyQuestion());
    }};

    private String pregunta;

    public AnalizadorPregunta(final String pregunta) {
        this.pregunta = pregunta;
    }

    public IPregunta toPregunta() {
        if(pregunta == null || "".equalsIgnoreCase(pregunta)) return new PreguntaIndefinida();
        return listaConstruirTipoPregunta(pregunta);
   	}
    
    private IPregunta listaConstruirTipoPregunta(String pregunta) {

    	IPregunta mapaPreguntas = buscarPregunta(pregunta.trim());

        return mapaPreguntas;
    }

    private IPregunta buscarPregunta(final String preguntaString) {
    	
    	for(String questionTypeString : TiposPreguntas.getValidQuestionValues()){
    		if(preguntaString.toUpperCase().contains(questionTypeString) && 
    				preguntaString.toUpperCase().trim().indexOf(questionTypeString) == PREGUNTA_EMPIEZA_INDEX){
    	        return mapaPreguntas.get(questionTypeString.toUpperCase());
    		}
    	}
    	return new PreguntaIndefinida();
    	
    }
}
