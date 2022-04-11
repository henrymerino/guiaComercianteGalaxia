package com.comerciante.galaxia.simbolos;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class PalabrasDiccionario implements Serializable{

	private static final long serialVersionUID = 1L;

	private Map<String, String> mapaPalbrasDiccionario = new HashMap<String, String>();
	
	public void agregarComandoValor(String nombreComandos, String comandoCorrespondiente){
		this.mapaPalbrasDiccionario.put(nombreComandos, comandoCorrespondiente);
	}
	
	public Set<String> getPalbrasConocidas(){
		return this.mapaPalbrasDiccionario.keySet();
	}
	
	public String getSimboloPalabra(String nombre){
		return mapaPalbrasDiccionario.get(nombre);
	}

}
