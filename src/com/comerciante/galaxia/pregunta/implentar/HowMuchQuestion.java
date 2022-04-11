package com.comerciante.galaxia.pregunta.implentar;

import com.comerciante.galaxia.pregunta.interfaz.IPregunta;
import com.comerciante.galaxia.reglas.Comerciante;

public class HowMuchQuestion implements IPregunta {

	@Override
	public void respuestaPregunta(Comerciante comerciante, String preguntaString) {
		comerciante.respuestaHowMuchPregunta(preguntaString);
	}

}
