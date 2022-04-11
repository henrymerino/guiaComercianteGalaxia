package com.comerciante.galaxia.simbolos;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/*
 * Clase ValorArticulo permite agregar y devolver el valor 
 */
public class ValorArticulo implements Serializable {

	private static final long serialVersionUID = 1L;
    
	private Map<String, Long> mapaValorArticulo = new HashMap<String, Long>();
	
	public void agregarComandoValor(String nombreArticulo, Long valor){
		this.mapaValorArticulo.put(nombreArticulo, valor);
	}
	
	public Long getValorArticulo(String nombreArticulo, Long contador) {
		return mapaValorArticulo.get(nombreArticulo) * contador;
	}
	
	public Set<String> getKnownItems(){
		return this.mapaValorArticulo.keySet();
	}
	

}
