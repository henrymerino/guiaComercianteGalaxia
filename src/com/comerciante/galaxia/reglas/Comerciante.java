package com.comerciante.galaxia.reglas;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.comerciante.galaxia.analizarcomando.AnalizadorComandos;
import com.comerciante.galaxia.analizarcomando.AnalizadorPregunta;
import com.comerciante.galaxia.comando.interfaz.IComando;
import com.comerciante.galaxia.excepciones.ReglasVioladasExcepcion;
import com.comerciante.galaxia.excepciones.SimboloDesconocidoExcepcion;
import com.comerciante.galaxia.pregunta.interfaz.IPregunta;
import com.comerciante.galaxia.preguntas.TiposPreguntas;
import com.comerciante.galaxia.respuestas.Respuestas;
import com.comerciante.galaxia.simbolos.PalabrasDiccionario;
import com.comerciante.galaxia.simbolos.Simbolos;
import com.comerciante.galaxia.simbolos.ValorArticulo;


public class Comerciante {
	
	private Simbolos simbolosComerciante;
	private Respuestas respuestasComerciante;
	private ValorArticulo valorArticulo;
	private Reglas reglas;
	private PalabrasDiccionario palabrasDiccionario; 
	
	
	public Comerciante(Simbolos simbolosComerciante){
		this.simbolosComerciante = simbolosComerciante;
		
		this.palabrasDiccionario = new PalabrasDiccionario();
		this.valorArticulo = new ValorArticulo();
		this.respuestasComerciante = new Respuestas();
		this.reglas = new Reglas();
	}
	
	@SuppressWarnings("rawtypes")
	public void comercio(List<String> consultas) {

		for (String comando : consultas) {
			IComando comandosComerciante = new AnalizadorComandos(comando.toUpperCase(),
					palabrasDiccionario).toCommand(this.getSimbolosComerciante());
			comandosComerciante.ejecutarComando(this, comando.toUpperCase());
		}
		
		Iterator it = this.respuestasComerciante.getMapaPreguntasRespuestas().entrySet().iterator();
		while (it.hasNext()) {
	        Map.Entry paerjas = (Map.Entry)it.next();
	        System.out.println(paerjas.getKey() + " : " + paerjas.getValue());
	        it.remove();
	    }
	}
	
	private List<String> parsePregunta(String preguntasString, TiposPreguntas preguntaType) {
		String[] preguntasSimbolo = preguntasString
				.split(preguntaType.getPreguntaString());
		List<String> listaPalabras = new ArrayList<String>();
		
		if (preguntasSimbolo.length != 2) {
			respuestasComerciante.anadirPreguntasRespuestas(preguntasString, "Pregunta y respuetas salio mal");
			return null;
		} else {
			String[] palabras = preguntasSimbolo[1].replaceAll("\\?", "").split(" ");
			for(String word: palabras){
				listaPalabras.add(word);
			}
			listaPalabras.remove("");
			return listaPalabras;
		}
	}
	
	public void informeComandoDesconocido() {
		System.err.println("Desconocido...");
	}
	
	public void delegarPregunta(String preguntaString) {
		IPregunta prguntaComando = new AnalizadorPregunta(preguntaString)
				.toPregunta();
		prguntaComando.respuestaPregunta(this, preguntaString);
	}
	
	public void respuestaHowMuchPregunta(String preguntaString) {
		List<String> palabras = parsePregunta(preguntaString, TiposPreguntas.HOW_MUCH_QUESTION);
		try{
			Long valor = this.reglas.calcularValor(this.simbolosComerciante, convertirRomano(palabras));
			if(valor != null){
				respuestasComerciante.anadirPreguntasRespuestas(preguntaString, "" +valor);
			}else{
				respuestasComerciante.anadirPreguntasRespuestas(preguntaString, "Algo fallo revisar!!");
			}
		}catch(SimboloDesconocidoExcepcion usEx){
			System.err.println("Simbolo desconociod en la pregunta "+ preguntaString);
		}catch (ReglasVioladasExcepcion rvEx) {
			System.err.println("Regla violada al procesar el comando "+ preguntaString);
		}

	}
	
	public void respuestataManyPregunta(String questionString) {
		try{
			List<String> words = parsePregunta(questionString, TiposPreguntas.HOW_MANY_QUESTION);
			int i = 0;
			while(palabrasDiccionario.getSimboloPalabra(words.get(i)) != null){
				i++;
			}
			String itemName = words.get(i);
			words = words.subList(0, i);
			Long itemCount = this.reglas.calcularValor(this.simbolosComerciante, convertirRomano(words));
	
			if(valorArticulo.getKnownItems().contains(itemName)){
				respuestasComerciante.anadirPreguntasRespuestas(questionString,
						""+valorArticulo.getValorArticulo(itemName, itemCount));
			}else{
				respuestasComerciante.anadirPreguntasRespuestas(questionString,
						"No se encontro definición del articulo");
			}
		}catch(SimboloDesconocidoExcepcion usEx){
			System.err.println("Simbolo desconociod en la pregunta "+ questionString);
		}catch (ReglasVioladasExcepcion rvEx) {
			System.err.println("Regla violada al procesar el comando "+ questionString);
		}
		
	}
	
	private String convertirRomano(List<String> palabras) {
		String romanString = "";
		for(String palabra: palabras){
			if(palabrasDiccionario.getSimboloPalabra(palabra) != null){
				romanString += palabrasDiccionario.getSimboloPalabra(palabra);
			}else{
				System.err.println("Simbolo no valido para : " + palabra);
				return null;
			}
		}
		return romanString;
	}

	public void agregarPalabraDiccionario(String comandoString) {
		String[] commands = comandoString.trim().split(" ");

		if (palabrasDiccionario.getSimboloPalabra(commands[0]) == null) {
			palabrasDiccionario.agregarComandoValor(commands[0], commands[2]);
		} else {
			informeReDefinicion(commands[0]);
		}
		System.out.println(commands[0]
				+ " Agregado al diccionario. Valor es : "
				+ this.simbolosComerciante.getValorSimbolobyName(palabrasDiccionario
						.getSimboloPalabra(commands[0])));

	}
	private void informeReDefinicion(String name) {
		System.err.println("Re definir para " + name + ". Ignorado!");
	}
	
	public void agregarValorArticulo(String comandoString) {
		System.out.println("Agregando valor al articulo...");
		
		String[] palabras = comandoString.split(" ");
		int i = 0;
		List<String> listaSimbolos= new ArrayList<String>();
		
		while(palabrasDiccionario.getSimboloPalabra(palabras[i]) != null){
			listaSimbolos.add(palabras[i]);
			i++;
		}
		String itemName = palabras[i];
		
		Long totalValor = Long.valueOf(palabras[i+2]);
		try{
			Long itemCount = this.reglas.calcularValor(this.simbolosComerciante, convertirRomano(listaSimbolos));
			this.valorArticulo.agregarComandoValor(itemName, totalValor/itemCount);
		}catch(SimboloDesconocidoExcepcion usEx){
			System.err.println("Simbolo desconociod en la pregunta "+ comandoString);
		}catch (ReglasVioladasExcepcion rvEx) {
			System.err.println("Regla violada al procesar el comando "+ comandoString);
		}
		

	}
	public void respuestaIndefinidaPregunta(String preguntaString) {
		respuestasComerciante.anadirPreguntasRespuestas(preguntaString,
				"No idea what you are talking about!");
	}

	public Simbolos getSimbolosComerciante() {
		return simbolosComerciante;
	}
	

}
