package com.comerciante.galaxia.convenio;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.comerciante.galaxia.simbolos.Simbolos;

/*
 * Clase GalaxiaConvenio guarda los numoeros romanos para los convenios de la galaxia
 */
public class GalaxiaConvenio extends Simbolos implements Serializable {

	private static final long serialVersionUID = 1L;

	private String nombreSimbolo;

	private Long valorSimbolo;

	public GalaxiaConvenio() {
		super();
	}

	/*
	 * metodo: simbolosValidos
	 * descripcion: letras que representan los numeors romanos 
	 */
	public enum simbolosValidos {

		I("I"), V("V"), X("X"), L("L"), C("C"), D("D"), M("M");

		String value;

		simbolosValidos(String value) {
			this.value = value;
		}

		@Override
		public String toString() {
			return this.value;
		}

		public static simbolosValidos getValidSymbol(String value) {
			for (simbolosValidos symbol : simbolosValidos.values()) {
				if (symbol.toString().equalsIgnoreCase(value))
					return symbol;
			}
			return null;
		}

	}

	@SuppressWarnings("serial")
	private static Map<String, Long> mapaSimbolos = new HashMap<String, Long>() {
		{
			put(simbolosValidos.I.toString(), 1L);
			put(simbolosValidos.V.toString(), 5L);
			put(simbolosValidos.X.toString(), 10L);
			put(simbolosValidos.L.toString(), 50L);
			put(simbolosValidos.C.toString(), 100L);
			put(simbolosValidos.D.toString(), 500L);
			put(simbolosValidos.M.toString(), 1000L);
		}
	};

	public String getNombreSimbolo() {
		return nombreSimbolo;
	}

	public Long getValorSimbolo() {
		return valorSimbolo;
	}

	public Long getSymbolValueBySymbolName(String symbolName) {
		return mapaSimbolos.get(new String(symbolName));
	}

	public Map<String, Long> getGalaxiaSimbos() {
		return mapaSimbolos;
	}

}
